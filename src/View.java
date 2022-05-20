import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class View extends Runner {
    // View uses Swing framework to display UI to user



    static JLabel[] Graphicmap = new JLabel[1];
    static int win =0;

    public void initializeMap(Map MAP1)
    {
        final Player[] Player1 = {null};
        for (int i = 0; i < MAP1.getRows(); i++) {
            for (int j = 0; j < MAP1.getColumns(); j++) {
                if (MAP1.getSingleElement(i, j) == P) {
                    Player1[0] = new Player(i, j, false);
                }
            }
        }
        //Same for the crates
        List<Crate> Crates = new ArrayList<Crate>();
        //Creating new Crates depending on the "C" in the Matrix
        for (int i = 0; i < MAP1.getRows(); i++) {
            for (int j = 0; j < MAP1.getColumns(); j++) {
                if (MAP1.getSingleElement(i, j) == C) {
                    Crates.add(new Crate(i, j, false));
                }
            }
        }
}

    public View (Map MAP1) {
        int lvl1rows = Level_1.length;
        int lvl1cols = Level_1.length;
        int lvl2rows = Level_2.length;
        int lvl2cols = Level_2.length;


        // Creation of the icon to pu ont the labels
        ImageIcon crateImg = new ImageIcon("crate.png");
        ImageIcon grassImg = new ImageIcon("blank.png");
        ImageIcon stoneImg = new ImageIcon("wall.png");
        ImageIcon playerImg = new ImageIcon("player.png");
        ImageIcon cratemImg = new ImageIcon("cratemarked.png");
        ImageIcon markImg = new ImageIcon("blankmarked.png");


        //resize all the label so it's bigger label->image->resized image-> label resized
        Image imagecrate = crateImg.getImage(); // transform it
        Image newimgcrate = imagecrate.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        crateImg = new ImageIcon(newimgcrate);  // transform it back

        Image imagegrass = grassImg.getImage(); // transform it
        Image newimggrass = imagegrass.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        grassImg = new ImageIcon(newimggrass);  // transform it back

        Image imagestone = stoneImg.getImage(); // transform it
        Image newimgstone = imagestone.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        stoneImg = new ImageIcon(newimgstone);  // transform it back

        Image imageplayer = playerImg.getImage(); // transform it
        Image newimgplayer = imageplayer.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        playerImg = new ImageIcon(newimgplayer);  // transform it back

        Image imagecratem = cratemImg.getImage(); // transform it
        Image newimgcratem = imagecratem.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        cratemImg = new ImageIcon(newimgcratem);  // transform it back

        Image imagemark = markImg.getImage(); // transform it
        Image newimgmark = imagemark.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        markImg = new ImageIcon(newimgmark);  // transform it back

        JPanel gridPanel = new JPanel(new GridLayout(lvl1rows, lvl1cols));





        JFrame frame = new JFrame("Key Listener");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(64 * lvl1rows, 64 * lvl1cols));

        Container contentPane = frame.getContentPane();

        //No need to press a key to print with this
        gridPanel.removeAll();
        JLabel label = null;
        for (int i = 0; i < Level_1.length; i++) { // goes through
            List<Crate> Crates = null;


            for (int j = 0; j < Level_1[i].length; j++) { //the matrix
                label = new JLabel();
                //label.setText(""); //to delete the string from the matrix map (you can try without)
                if (MAP1.getSingleElement(i, j) == P) {//check if it is a player
                    label.setIcon(playerImg); //give this specific label the player picture
                } else if (MAP1.getSingleElement(i, j) == G) { //same
                    label.setIcon(grassImg);
                } else if (MAP1.getSingleElement(i, j) == S) {
                    label.setIcon(stoneImg);
                } else if (MAP1.getSingleElement(i, j) == M) {
                    label.setIcon(markImg);
                } else if (MAP1.getSingleElement(i, j) == C) {

                    for (int k = 0; k < Crates.size(); k++) { //move through the list
                        Crate element = Crates.get(k); //creates a create with receive the crate actually tested
                        if (element.getRowPos() == i && element.getColPos() == j) { // for this crate especially test IsOnMark
                            if (element.IsOnMark == false) {
                                label.setIcon(crateImg);
                            } else if (element.IsOnMark) {
                                label.setIcon(cratemImg);
                            }
                        }
                    }

                    /*for (Crate crate : Crates) { //try to go through every crates but not working
                        if (crate.IsOnMark == false) {
                            label.setIcon(crateImg);
                        } else if (crate.IsOnMark) { //only once the last crate (in the list) is on a mark, then put every crates as marked crate
                            label.setIcon(cratemImg);
                        }
                    }
                }*/
                }
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);

                gridPanel.add(label);

            }

            //function no happy if there's no final stuff
            ImageIcon finalPlayerImg = playerImg;
            ImageIcon finalGrassImg = grassImg;
            ImageIcon finalStoneImg = stoneImg;
            ImageIcon finalMarkImg = markImg;
            ImageIcon finalCrateImg = crateImg;
            ImageIcon finalCratemImg = cratemImg;


            KeyListener listener = new KeyListener() {
                @Override
                public void keyPressed(KeyEvent event) {
                    Runner.printEventInfo(event);
                }

                @Override
                public void keyReleased(KeyEvent event) {
                }

                @Override
                public void keyTyped(KeyEvent event) {
                }

                private void printEventInfo(KeyEvent e) {

                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_RIGHT:
                            MAP1.MoveRight(Player1[0], MAP1, Crates);
                            break;
                        case KeyEvent.VK_LEFT:
                            MAP1.MoveLeft(Player1[0], MAP1, Crates);
                            break;
                        case KeyEvent.VK_DOWN:
                            MAP1.MoveDown(Player1[0], MAP1, Crates);
                            break;
                        case KeyEvent.VK_UP:
                            MAP1.MoveUp(Player1[0], MAP1, Crates);
                            break;

                    }
                    //-------Victory system
                    int count = 0;
                    for (Crate crate : Crates) {
                        if (crate.IsOnMark) {
                            count++;
                        }
                    }
                    if ((count >= Crates.size())) {

                        if ((win == 0)) {
                            //first level
                            System.out.println("Level 1 clear\n");
                            //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                            MAP1.setElements(Level_2);

                            for (int i = 0; i < MAP1.getRows(); i++) {
                                for (int j = 0; j < MAP1.getColumns(); j++) {
                                    if (MAP1.getSingleElement(i, j) == P) {
                                        Player1[0] = new Player(i, j, false);
                                    }
                                }
                            }

                            Crates.clear(); //don't remember what it does, but useful
                            //Creating new Crates depending on the "C" in the Matrix
                            for (int i = 0; i < MAP1.getRows(); i++) {
                                for (int j = 0; j < MAP1.getColumns(); j++) {
                                    if (MAP1.getSingleElement(i, j) == C) {
                                        Crates.add(new Crate(i, j, false));
                                    }
                                }
                                count = 0;
                            }
                        }
                        win = 1;
                        //count=0;
                        for (Crate crate : Crates) {
                            if (crate.IsOnMark) {
                                count++;
                            }
                        }
                        if (count >= Crates.size()) {
                            System.out.println("Victory!");
                            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        }
                        count = 0;
                    }
                    /*for (Crate crate : Crates){
                        System.out.println(crate +"On mark : " +crate.getIsOnMark());
                    }*/
                    //-----end victory system
                    System.out.println(MAP1);
                    gridPanel.removeAll();
                    JLabel label = null;
                    //Same as before
                    for (int i = 0; i < Level_1.length; i++) {
                        for (int j = 0; j < Level_1[i].length; j++) {
                            label = new JLabel();
                            //label.setText("");
                            if (MAP1.getSingleElement(i, j) == P) {
                                label.setIcon(finalPlayerImg);
                            } else if (MAP1.getSingleElement(i, j) == G) {
                                label.setIcon(finalGrassImg);
                            } else if (MAP1.getSingleElement(i, j) == S) {
                                label.setIcon(finalStoneImg);
                            } else if (MAP1.getSingleElement(i, j) == M) {
                                label.setIcon(finalMarkImg);
                            } else if (MAP1.getSingleElement(i, j) == C) {
                                for (int k = 0; k < Crates.size(); k++) {
                                    Crate element = Crates.get(k);
                                    if (element.IsOnMark == false) {
                                        label.setIcon(finalCrateImg);
                                    } else if (element.IsOnMark) {
                                        label.setIcon(finalCratemImg);
                                    }
                                }
                            }
                            label.setHorizontalAlignment(SwingConstants.CENTER);
                            gridPanel.add(label);
                        }
                    }
                    frame.setVisible(true);
                }
            };

            JTextField textField = new JTextField();
            textField.addKeyListener(listener);
            contentPane.add(textField, BorderLayout.NORTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(gridPanel);
            frame.pack();
            frame.setVisible(true);



    /*
    private JFrame frame;
    private JLabel firstnameLabel;
    private JLabel lastnameLabel;
    private JTextField firstnameTextfield;
    private JTextField lastnameTextfield;
    private JButton firstnameSaveButton;
    private JButton lastnameSaveButton;
    private JButton hello;
    private JButton bye;
    public View(String title) {
        frame = new JFrame(title);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 120);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        firstnameLabel = new JLabel("Firstname :");
        lastnameLabel = new JLabel("Lastname :");
        firstnameTextfield = new JTextField();
        lastnameTextfield = new JTextField();
        firstnameSaveButton = new JButton("Save firstname");
        lastnameSaveButton = new JButton("Save lastname");
        hello = new JButton("Hello!");
        bye = new JButton("Bye!");
        // Add UI element to frame
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
layout.setHorizontalGroup(layout.createSequentialGroup()
.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(firstnameLabel)
        .addComponent(lastnameLabel))
.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(firstnameTextfield)
        .addComponent(lastnameTextfield))
.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(firstnameSaveButton)
        .addComponent(lastnameSaveButton))
.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(hello)
        .addComponent(bye)));
layout.setVerticalGroup(layout.createSequentialGroup()
.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(firstnameLabel)
        .addComponent(firstnameTextfield).addComponent(firstnameSaveButton).addComponent(hello))
.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lastnameLabel)
        .addComponent(lastnameTextfield).addComponent(lastnameSaveButton).addComponent(bye)));
layout.linkSize(SwingConstants.HORIZONTAL, firstnameSaveButton, lastnameSaveButton);
layout.linkSize(SwingConstants.HORIZONTAL, hello, bye);
frame.getContentPane().setLayout(layout);
    }
    public JFrame getFrame() {
        return frame;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    public JLabel getFirstnameLabel() {
        return firstnameLabel;
    }
    public void setFirstnameLabel(JLabel firstnameLabel) {
        this.firstnameLabel = firstnameLabel;
    }
    public JLabel getLastnameLabel() {
        return lastnameLabel;
    }
    public void setLastnameLabel(JLabel lastnameLabel) {
        this.lastnameLabel = lastnameLabel;
    }
    public JTextField getFirstnameTextfield() {
        return firstnameTextfield;
    }
    public void setFirstnameTextfield(JTextField firstnameTextfield) {
        this.firstnameTextfield = firstnameTextfield;
    }
    public JTextField getLastnameTextfield() {
        return lastnameTextfield;
    }
    public void setLastnameTextfield(JTextField lastnameTextfield) {
        this.lastnameTextfield = lastnameTextfield;
    }
    public JButton getFirstnameSaveButton() {
        return firstnameSaveButton;
    }
    public void setFirstnameSaveButton(JButton firstnameSaveButton) {
        this.firstnameSaveButton = firstnameSaveButton;
    }
    public JButton getLastnameSaveButton() {
        return lastnameSaveButton;
    }
    public void setLastnameSaveButton(JButton lastnameSaveButton) {
        this.lastnameSaveButton = lastnameSaveButton;
    }
    public JButton getHello() {
        return hello;
    }
    public void setHello(JButton hello) {
        this.hello = hello;
    }
    public JButton getBye() {
        return bye;
    }
    public void setBye(JButton bye) {
        this.bye = bye;
    }


     */
        }
    }