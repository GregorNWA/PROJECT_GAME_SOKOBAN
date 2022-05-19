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

    static final String S = "  ▀▀  ";
    static final String P = "  P1  ";
    static final String G = "  --  ";
    static final String T = "  TT  ";
    static final String C = "  []  ";
    static final String M = "  ()  ";
    static final String MC = "  [.]  ";


    static JLabel[] Graphicmap = new JLabel[100];
    static int win =0;

    public static void main(String[] args) {
        String[][] Level_1 = {

               //0, 1, 2, 3, 4, 5, 6, 7, 8
                {S, S, S, S, S, S, S, S, G},//0
                {S, G, G, S, G, G, G, S, G},//1
                {S, G, G, G, C, G, G, S, G},//2
                {S, G, G, S, C, C, G, S, G},//3
                {S, S, G, S, G, G, G, S, G},//4
                {S, M, M, G, C, S, G, S, G},//5
                {S, M, M, G, G, G, P, S, G},//6
                {S, S, S, S, S, S, S, S, G},//7
                {G, G, G, G, G, G, G, G, G},//8
               //0, 1, 2, 3, 4, 5, 6, 7, 8
        };

        //Map level 2
        String[][] Level_2 = {
                //0, 1, 2, 3, 4, 5, 6, 7, 8
                {S, S, S, S, S, S, S, S, S},//0
                {S, M, M, G, C, G, M, M, S},//1
                {S, M, M, C, C, G, M, M, S},//2
                {S, G, G, C, G, C, C, G, S},//3
                {S, C, C, G, S, G, C, C, S},//4
                {S, G, C, C, G, C, G, G, S},//5
                {S, M, M, G, C, C, M, M, S},//6
                {S, M, M, G, C, P, M, M, S},//7
                {S, S, S, S, S, S, S, S, S},//8
                //0, 1, 2, 3, 4, 5, 6, 7, 8

        };


        //automatic matrix size recognition
        int lvl1rows = Level_1.length;
        int lvl1cols = Level_1.length;
        int lvl2rows = Level_2.length;
        int lvl2cols = Level_2.length;

        Map MAP1 = new Map(lvl1rows, lvl1cols);
        MAP1.setElements(Level_1);


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
                label = new JLabel(MAP1.getElements(i, j));
                label.setText(""); //to delete the string from the matrix map (you can try without)
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

                            if((win == 0)) {
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
                            count=0;
                    }
                    for (Crate crate : Crates){
                        System.out.println(crate +"On mark : " +crate.getIsOnMark());
                    }
                    //-----end victory system
                    System.out.println(MAP1);
                    gridPanel.removeAll();
                    JLabel label = null;
                    //Same as before
                    for (int i = 0; i < Level_1.length; i++) {
                        for (int j = 0; j < Level_1[i].length; j++) {
                            label = new JLabel(MAP1.getElements(i, j));
                            label.setText("");
                            if (MAP1.getSingleElement(i, j) == P) {
                                label.setIcon(finalPlayerImg);
                            }
                            else if (MAP1.getSingleElement(i, j) == G) {
                                label.setIcon(finalGrassImg);
                            }
                            else if (MAP1.getSingleElement(i, j) == S) {
                                label.setIcon(finalStoneImg);
                            }
                            else if (MAP1.getSingleElement(i, j) == M) {
                                label.setIcon(finalMarkImg);
                            }
                            else if (MAP1.getSingleElement(i, j) == C) {
                                for(int k = 0; k < Crates.size();k++){
                                    Crate element = Crates.get(k);
                                    if(element.getRowPos()==i && element.getColPos()==j) {
                                        if (element.IsOnMark == false) {
                                            label.setIcon(finalCrateImg);
                                        } else if (element.IsOnMark) {
                                            label.setIcon(finalCratemImg);
                                        }
                                    }
                                }
                                /*for (Crate crate : Crates) {
                                    if (crate.IsOnMark == false) {
                                        label.setIcon(finalCrateImg);
                                    } else if (crate.IsOnMark) {
                                        label.setIcon(finalCratemImg);
                                    }
                                }*/
                            }
                            label.setHorizontalAlignment(SwingConstants.CENTER);
                            gridPanel.add(label);
                        }
                    }
                    frame.setVisible(true);
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