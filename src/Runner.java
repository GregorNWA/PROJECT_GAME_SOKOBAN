import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;



public class Runner {
    private String[][] elements;
    private int rows;
    private int columns;



    static JLabel[] Graphicmap = new JLabel[100];

    //should move to MAP


    public static void main(String[] args) {

        //automatic matrix size recognition


        JPanel gridPanel = new JPanel(new GridLayout(lvl1rows, lvl1cols));


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


        //Creating new Player depending on "P" in Matrix
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
        System.out.println(MAP1);


        JFrame frame = new JFrame("Key Listener");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(64*lvl1rows, 64*lvl1cols));

        Container contentPane = frame.getContentPane();

        //No need to press a key to print with this
        gridPanel.removeAll();
        JLabel label = null;
        for (int i = 0; i < Level_1.length; i++) { // goes through
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

                    for(int k = 0; k < Crates.size();k++){ //move through the list
                        Crate element = Crates.get(k); //creates a create with receive the crate actually tested
                        if(element.getRowPos()==i && element.getColPos()==j) { // for this crate especially test IsOnMark
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
            Player finalPlayer = Player1[0];
            ImageIcon finalPlayerImg = playerImg;
            ImageIcon finalGrassImg = grassImg;
            ImageIcon finalStoneImg = stoneImg;
            ImageIcon finalMarkImg = markImg;
            ImageIcon finalCrateImg = crateImg;
            ImageIcon finalCratemImg = cratemImg;

            KeyListener listener = new KeyListener() {
                @Override
                public void keyPressed(KeyEvent event) {
                    printEventInfo(event);
                }

                @Override
                public void keyReleased(KeyEvent event) {
                }

                @Override
                public void keyTyped(KeyEvent event) {
                }


            };

            // Create stuff
            JTextField textField = new JTextField();
            textField.addKeyListener(listener);
            contentPane.add(textField, BorderLayout.NORTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(gridPanel);
            frame.pack();
            frame.setVisible(true);

        }
    }

}