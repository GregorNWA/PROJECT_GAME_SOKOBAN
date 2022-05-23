import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class View implements ViewObserver {
    JPanel gridPanel = new JPanel(new GridLayout(Map.lvl1rows, Map.lvl1cols));
    ImageIcon crateImg = new ImageIcon("crate.png");
    ImageIcon grassImg = new ImageIcon("blank.png");

    ImageIcon stoneImg = new ImageIcon("wall.png");
    ImageIcon playerImg = new ImageIcon("fabian1-blank.png");
    ImageIcon cratemImg = new ImageIcon("cratemarked.png");
    ImageIcon markImg = new ImageIcon("blankmarked.png");

    JFrame frame = new JFrame("Key Listener");
    JPanel panel = (JPanel) frame.getContentPane();
    // View uses Swing framework to display UI to user
    private final Map mapV;
    int sqsize = 80;//length of the square


    KeyListener listener = new KeyListener() {
        @Override
        public void keyPressed(KeyEvent event) {
            //Maybe create new COntroller controller before
            //Controller.directionInput(event);
            switch (event.getKeyCode()) {
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> Controller.directionInput("RIGHT");
                case KeyEvent.VK_LEFT, KeyEvent.VK_A -> Controller.directionInput("LEFT");
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> Controller.directionInput("DOWN");
                case KeyEvent.VK_UP, KeyEvent.VK_W -> Controller.directionInput("UP");
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
        //Print new Map to Console
        gridPanel.removeAll();
        JLabel label = null;
        //Same as before
        for (int i = 0; i < Map.Level_1.length; i++) {
            for (int j = 0; j < Map.Level_1[i].length; j++) {
                label = new JLabel();
                if (mapV.getSingleElement(i, j) == Map.P) {
                    label.setIcon(playerImg);
                } else if (mapV.getSingleElement(i, j) == Map.G) {
                    label.setIcon(grassImg);
                } else if (mapV.getSingleElement(i, j) == Map.S) {
                    label.setIcon(stoneImg);
                } else if (mapV.getSingleElement(i, j) == Map.M) {
                    label.setIcon(markImg);
                } else if (mapV.getSingleElement(i, j) == Map.C) {
                    for (int k = 0; k < mapV.getCrates().size(); k++) {
                        Crate element = mapV.getCrates().get(k);
                        if (element.getRowPos() == i && element.getColPos() == j) {
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
                    Controller.directionInput("LEFT");
                });
        RightButton.addActionListener
                (event -> {
                    Controller.directionInput("RIGHT");
                });
        DownButton.addActionListener
                (event -> {
                    Controller.directionInput("DOWN");
                });
        UpButton.addActionListener
                (event -> {
                    Controller.directionInput("UP");
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


    public View(Map m) {
        mapV = m;
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

    public void closeWindow() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}