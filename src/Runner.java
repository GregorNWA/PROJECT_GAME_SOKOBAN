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


    static JLabel[] Graphicmap = new JLabel[36];
    static int win =0;

    public static void main(String[] args) {
        //Note: Add new Player with Position from Matrix, not manual positions)
        /*String[][] MAP = {
               //0,1,2,3,4,5
                {S,S,G,S,S,S},//0
                {G,G,G,G,C,S},//1
                {G,G,M,G,C,S},//2
                {S,G,M,G,S,S},//3
                {S,G,C,G,S,S},//4
                {S,S,P,S,S,G},//5
        };     //0,1,2,3,4,5*/

        //Map move down
        String[][] Level_1 = {
                //0,1,2,3,4,5
                {G, G, P, G, G, G},//0
                {M, C, C, C, M, G},//1
                {G, M, M, M, G, G},//2
                {G, G, M, G, G, G},//3
                {G, G, G, G, G, G},//4
                {G, G, G, G, G, G},//5
        };     //0,1,2,3,4,5

        //MAP move right
        String[][] Level_2 = {
               //0, 1, 2, 3, 4, 5
                {S, S, S, S, S, S},//0
                {S, P, G, G, G, S},//1
                {S, G, M, C, G, S},//2
                {S, M, M, C, G, S},//3
                {S, G, M, C, G, S},//4
                {S, S, S, S, S, S},//5
        };     //0, 1, 2, 3, 4, 5


        //automatic matrix size recognition
        int lvl1rows = Level_1.length;
        int lvl1cols = Level_1.length;
        int lvl2rows = Level_2.length;
        int lvl2cols = Level_2.length;

        Map MAP1 = new Map(lvl1rows, lvl1cols);
        MAP1.setElements(Level_1);

        //MAP1.setElements(Level_1);
        /*if (blabla level 1){
            JPanel gridPanel = new JPanel(new GridLayout(lvl1rows, lvl1cols));
        }
        else if (/*blabla lvl2){
            JPanel gridPanel = new JPanel(new GridLayout(lvl2rows, lvl2cols));

        }*/

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


        List<Crate> Crates = new ArrayList<Crate>();
        //Creating new Crates depending on the "C" in the Matrix
        for (int i = 0; i < MAP1.getRows(); i++) {
            for (int j = 0; j < MAP1.getColumns(); j++) {
                if (MAP1.getSingleElement(i, j) == C) {
                    Crates.add(new Crate(i, j, false));
                }
            }
        }


        //System.out.println(Crates_nbr);

        System.out.println(MAP1);


        JFrame frame = new JFrame("Key Listener");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(384, 384));

        Container contentPane = frame.getContentPane();

        //No need to press a key to print with this
        gridPanel.removeAll();
        JLabel label = null;
        for (int i = 0; i < Level_1.length; i++) {  // avoid using "magic" numbers
            for (int j = 0; j < Level_1[i].length; j++) {
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
                    for (Crate crate : Crates) { //try to go through every crates but not working
                        if (crate.IsOnMark == false) {
                            label.setIcon(crateImg);
                        } else if (crate.IsOnMark) { //only once the last crate (in the list) is on a mark, then put every crates as marked crate
                            label.setIcon(cratemImg);
                        }
                    }
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
                                System.out.println("SIEG\n");
                                //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                                MAP1.setElements(Level_2);

                                for (int i = 0; i < MAP1.getRows(); i++) {
                                    for (int j = 0; j < MAP1.getColumns(); j++) {
                                        if (MAP1.getSingleElement(i, j) == P) {
                                            Player1[0] = new Player(i, j, false);
                                        }
                                    }
                                }

                                Crates.clear();
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
                                System.out.println("SIEG");
                                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                                System.out.println("j'ai win fdp");
                        }
                            count=0;

                    }
                    for (Crate crate : Crates){
                        System.out.println(crate +"On mark : " +crate.getIsOnMark());
                    }

                    //-----end victory system
                    System.out.println(MAP1);
                    //JPanel gridPanel2 = new JPanel(new GridLayout(6, 6));
                    gridPanel.removeAll();
                    //gridPanel.revalidate();
                    //gridPanel.repaint();
                    JLabel label = null;

                    //same as before
                    for (int i = 0; i < Level_1.length; i++) {  // avoid using "magic" numbers
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
                                for (Crate crate : Crates) {
                                    if (crate.IsOnMark == false) {
                                        label.setIcon(finalCrateImg);
                                    } else if (crate.IsOnMark) {
                                        label.setIcon(finalCratemImg);
                                    }
                                }
                            }
                            label.setHorizontalAlignment(SwingConstants.CENTER);
                            //label.repaint();
                            //Graphicmap[4 * i + j] = label;
                            //gridPanel2.removeAll();
                            //gridPanel2.add(label);
                            gridPanel.add(label);

                            //label.repaint();
                            //frame.repaint();

                        }
                    }
                    //SwingUtilities.updateComponentTreeUI(frame);
                    //frame.removeAll();
                    //panel.updateUI();
                    //frame.update();
                    //frame.add(gridPanel2);  //maybe to put again
                    //System.out.println(frame);
                    //frame.add(gridPanel);
                    //System.out.println("here ");
                    //frame.pack();
                    frame.setVisible(true);

                }
            };

            // Create stuff
            JTextField textField = new JTextField();
            textField.addKeyListener(listener);
            contentPane.add(textField, BorderLayout.NORTH);
            //gridPanel.add(textField, BorderLayout.NORTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(gridPanel);
            frame.pack();
            frame.setVisible(true);

        }
    }
}