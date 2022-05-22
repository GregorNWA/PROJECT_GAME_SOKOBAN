import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.*;

public class View extends Runner  {
    // View uses Swing framework to display UI to user
    private Map mapV;
    private View view;

    Controller controller = new Controller(mapV, view);


    static JLabel[] Graphicmap = new JLabel[1];
    static int win = 0;

    public View(Map mapV) {
        this.mapV = mapV;
    };
        /*int lvl1rows = Map.Level_1.length;
        int lvl1cols = Map.Level_1.length;
        int lvl2rows = Map.Level_2.length;
        int lvl2cols = Map.Level_2.length;
*/

        // Creation of the icon to pu ont the labels
    public void graphics() {
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

        JPanel gridPanel = new JPanel(new GridLayout(Map.lvl1rows, Map.lvl1cols));


        JFrame frame = new JFrame("Key Listener");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(64 * Map.lvl1rows, 64 * Map.lvl1cols));

        Container contentPane = frame.getContentPane();

        //No need to press a key to print with this
        gridPanel.removeAll();
        JLabel label = null;
        for (int i = 0; i < Map.Level_1.length; i++) { // goes through
            for (int j = 0; j < Map.Level_1[i].length; j++) { //the matrix
                label = new JLabel();
                //label.setText(""); //to delete the string from the matrix map (you can try without)
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

            //function no happy if there's no final stuff
            ImageIcon finalPlayerImg = playerImg;
            ImageIcon finalGrassImg = grassImg;
            ImageIcon finalStoneImg = stoneImg;
            ImageIcon finalMarkImg = markImg;
            ImageIcon finalCrateImg = crateImg;
            ImageIcon finalCratemImg = cratemImg;
        }

        JTextField textField = new JTextField();
        textField.addKeyListener(listener);
        contentPane.add(textField, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gridPanel);
        frame.pack();
        frame.setVisible(true);


    }

            KeyListener listener = new KeyListener() {
                @Override
                public void keyPressed(KeyEvent event) {
                    //Maybe create new COntroller controller before
                    controller.directionInput(event);
                }

                @Override
                public void keyReleased(KeyEvent event) {
                }

                @Override
                public void keyTyped(KeyEvent event) {
                }
                /*
                private void printEventInfo(KeyEvent e) {
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
                            mapV.setElements(Level_2);

                            for (int i = 0; i < mapV.getRows(); i++) {
                                for (int j = 0; j < mapV.getColumns(); j++) {
                                    if (mapV.getSingleElement(i, j) == P) {
                                        Player1[0] = new Player(i, j, false);
                                    }
                                }
                            }

                            Crates.clear(); //don't remember what it does, but useful
                            //Creating new Crates depending on the "C" in the Matrix
                            for (int i = 0; i < mapV.getRows(); i++) {
                                for (int j = 0; j < mapV.getColumns(); j++) {
                                    if (mapV.getSingleElement(i, j) == C) {
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
                    for (Crate crate : Crates){
                        System.out.println(crate +"On mark : " +crate.getIsOnMark());
                    }
                    //-----end victory system
                    System.out.println(mapV);
                    gridPanel.removeAll();
                    JLabel label = null;
                    //Same as before
                    for (int i = 0; i < Map.Level_1.length; i++) {
                        for (int j = 0; j < Map.Level_1[i].length; j++) {
                            label = new JLabel();
                            //label.setText("");
                            if (mapV.getSingleElement(i, j) == Map.P) {
                                label.setIcon(finalPlayerImg);
                            } else if (mapV.getSingleElement(i, j) == Map.G) {
                                label.setIcon(finalGrassImg);
                            } else if (mapV.getSingleElement(i, j) == Map.S) {
                                label.setIcon(finalStoneImg);
                            } else if (mapV.getSingleElement(i, j) == Map.M) {
                                label.setIcon(finalMarkImg);
                            } else if (mapV.getSingleElement(i, j) == Map.C) {
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
                            label.setVerticalAlignment(SwingConstants.CENTER);
                            gridPanel.add(label);
                        }
                    }
                    frame.setVisible(true);
                }

                 */
            };


        }



