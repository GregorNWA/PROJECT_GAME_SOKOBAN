import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class View implements ViewObserver {

    //Implementation of the icons and creation of the gridPanel
    JPanel gridPanel = new JPanel(new GridLayout(Map.lvl1rows, Map.lvl1cols));
    ImageIcon crateImg = new ImageIcon("crate.png");
    ImageIcon grassImg = new ImageIcon("blank.png");

    ImageIcon stoneImg = new ImageIcon("wall.png");
    ImageIcon playerImg = new ImageIcon("fabian1-blank.png");
    ImageIcon cratemImg = new ImageIcon("cratemarked.png");
    ImageIcon markImg = new ImageIcon("blankmarked.png");

    static JFrame frame = new JFrame("Key Listener");
    JPanel panel = (JPanel) frame.getContentPane();

    private int sqsize = 70;//length of the square easier to resize the icons
    Controller c;
    private Map mapV;
    private View viewV;

    //View's constructor

    public View(Map m) {
        mapV=m;
        c=new Controller(m,viewV);
    }

    //creation of the keyListener
    KeyListener listener = new KeyListener() {
        @Override
        public void keyPressed(KeyEvent event) {
            //Detect whether if an arrow is pressed or WASD
            switch (event.getKeyCode()) {
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> c.directionInput("RIGHT");
                case KeyEvent.VK_LEFT, KeyEvent.VK_A -> c.directionInput("LEFT");
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> c.directionInput("DOWN");
                case KeyEvent.VK_UP, KeyEvent.VK_W -> c.directionInput("UP");
            }
        }
        @Override
        public void keyReleased(KeyEvent event) {
        }

        @Override
        public void keyTyped(KeyEvent event) {
        }
    };

    @Override
    public void updateMap() {
        gridPanel.removeAll(); //clear the grid panel to apply all the labels again
        JLabel label = null;
        //goes through the whole matrix
        for (int i = 0; i < Map.Level_1.length; i++) {
            for (int j = 0; j < Map.Level_1[i].length; j++) {
                label = new JLabel();
                if (mapV.getSingleElement(i, j) == Map.P) {//If player
                    label.setIcon(playerImg); //
                } else if (mapV.getSingleElement(i, j) == Map.G) {
                    label.setIcon(grassImg);
                } else if (mapV.getSingleElement(i, j) == Map.S) {
                    label.setIcon(stoneImg);
                } else if (mapV.getSingleElement(i, j) == Map.M) {
                    label.setIcon(markImg);
                } else if (mapV.getSingleElement(i, j) == Map.C) {
                    for (int k = 0; k < mapV.getCrates().size(); k++) { //Goes through the list of crate
                        Crate element = mapV.getCrates().get(k); //Get the index and create a new crate with
                        // the same properties and position
                        if (element.getRowPos() == i && element.getColPos() == j) {//M
                            if (element.IsOnMark == false) {
                                label.setIcon(crateImg);

                            } else if (element.IsOnMark) {
                                label.setIcon(cratemImg);
                            }
                        }
                    }
                }
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                gridPanel.add(label);
            }
        }
        frame.setVisible(true);
    }


    /**
     * Buttons
     */
    JButton LeftButton = new JButton("LEFT");
    JButton RightButton = new JButton("RIGHT");
    JButton DownButton = new JButton("DOWN");
    JButton UpButton = new JButton("UP");

    JPanel ButtonPanel = new JPanel();
    JPanel BotPanel = new JPanel();

    public void addButtons() {
        LeftButton.addActionListener
                (event -> {
                    c.directionInput("LEFT");
                });
        RightButton.addActionListener
                (event -> {
                    c.directionInput("RIGHT");
                });
        DownButton.addActionListener
                (event -> {
                    c.directionInput("DOWN");
                });
        UpButton.addActionListener
                (event -> {
                    c.directionInput("UP");
                });

        ButtonPanel.setPreferredSize(new Dimension(150, 100));
        ButtonPanel.setLayout(new BorderLayout());
        ButtonPanel.add(DownButton, BorderLayout.PAGE_END);
        ButtonPanel.add(UpButton, BorderLayout.PAGE_START);
        ButtonPanel.add(LeftButton, BorderLayout.LINE_START);
        ButtonPanel.add(RightButton, BorderLayout.LINE_END);

        ButtonPanel.setFocusable(false);
        LeftButton.setFocusable(false);
        RightButton.setFocusable(false);
        DownButton.setFocusable(false);
        UpButton.setFocusable(false);
        BotPanel.add(ButtonPanel, BorderLayout.CENTER);
    }




    public void graphics() {

        //resize all the label so it's bigger label->image->resized image-> label resized
        Image imagecrate = crateImg.getImage(); // transform it
        Image newimgcrate = imagecrate.getScaledInstance(sqsize, sqsize, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        crateImg = new ImageIcon(newimgcrate);  // transform it back

        Image imagegrass = grassImg.getImage(); // transform it
        Image newimggrass = imagegrass.getScaledInstance(sqsize, sqsize, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        grassImg = new ImageIcon(newimggrass);  // transform it back

        Image imagestone = stoneImg.getImage(); // transform it
        Image newimgstone = imagestone.getScaledInstance(sqsize, sqsize, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        stoneImg = new ImageIcon(newimgstone);  // transform it back

        Image imageplayer = playerImg.getImage(); // transform it
        Image newimgplayer = imageplayer.getScaledInstance(sqsize, sqsize, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        playerImg = new ImageIcon(newimgplayer);  // transform it back

        Image imagecratem = cratemImg.getImage(); // transform it
        Image newimgcratem = imagecratem.getScaledInstance(sqsize, sqsize, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        cratemImg = new ImageIcon(newimgcratem);  // transform it back

        Image imagemark = markImg.getImage(); // transform it
        Image newimgmark = imagemark.getScaledInstance(sqsize, sqsize, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        markImg = new ImageIcon(newimgmark);  // transform it back

        panel.setPreferredSize(new Dimension(sqsize * Map.lvl1rows, sqsize * Map.lvl1cols));

        //No need to press a key to print with this
        gridPanel.removeAll();
        JLabel label = null;
        for (int i = 0; i < Map.Level_1.length; i++) { // goes through
            for (int j = 0; j < Map.Level_1[i].length; j++) { //the matrix
                label = new JLabel();
                if (mapV.getSingleElement(i, j) == Map.P) {//check if it is a player
                    label.setIcon(playerImg); //give this specific label the player picture
                } else if (mapV.getSingleElement(i, j) == Map.G) { //same
                    label.setIcon(grassImg);
                } else if (mapV.getSingleElement(i, j) == Map.S) {
                    label.setIcon(stoneImg);
                } else if (mapV.getSingleElement(i, j) == Map.M) {
                    label.setIcon(markImg);
                } else if (mapV.getSingleElement(i, j) == Map.C) {
                    for (int k = 0; k < mapV.getCrates().size(); k++) { //move through the list
                        Crate element = mapV.getCrates().get(k); //creates a create with receive the crate actually tested
                        if (element.getRowPos() == i && element.getColPos() == j) { // for this crate especially test IsOnMark
                            if (element.IsOnMark == false) {
                                label.setIcon(crateImg);
                            } else if (element.IsOnMark) {
                                label.setIcon(cratemImg);
                            }
                        }
                    }

                }
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                gridPanel.add(label);
            }
        }
        frame.setPreferredSize(new Dimension(sqsize*Map.lvl1cols, (sqsize*Map.lvl1rows)+150));

        frame.add(BotPanel,BorderLayout.PAGE_END);
        BotPanel.setFocusable(false);

        frame.addKeyListener(listener);
        frame.setFocusable(true);
        frame.add(gridPanel,BorderLayout.PAGE_START);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public  void closeWindow() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}