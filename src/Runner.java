import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

    static final String S  = "  ▀▀  ";
    static final String P  = "  P1  ";
    static final String G  = "  --  ";
    static final String T  = "  TT  ";
    static final String C  = "  []  ";
    static final String M  = "  ()  ";
    static final String MC = "  [.]  ";


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
        /*String[][] MAP = {
               //0,1,2,3,4,5
                {S,S,P,S,S,S},//0
                {G,G,C,G,C,S},//1
                {G,G,M,G,C,S},//2
                {S,G,M,G,S,S},//3
                {S,G,G,G,S,S},//4
                {S,S,G,S,S,G},//5
        };     //0,1,2,3,4,5*/

        //MAP move right
        String[][] MAP = {
                //0,1,2,3,4,5
                {S, S, S, S, C, S},//0
                {G, G, G, G, G, S},//1
                {G, G, P, G, G, S},//2
                {S, M, C, G, S, S},//3
                {S, G, M, G, S, S},//4
                {S, S, G, S, S, G},//5
        };     //0,1,2,3,4,5

        Map MAP1 = new Map(6, 6);
        MAP1.setElements(MAP);

        //Creating new Player depending on "P" in Matrix
        Player Player1 = null;
        for (int i = 0; i < MAP1.getRows(); i++) {
            for (int j = 0; j < MAP1.getColumns(); j++) {
                if (MAP1.getSingleElement(i, j) == P) {
                    Player1 = new Player(i, j, false);
                }
            }
        }

        List<Crate> Crates = new ArrayList<Crate>();
        //Createing new Crates depending on the "C" in the Matrix
        for (int i = 0; i < MAP1.getRows(); i++) {
            for (int j = 0; j < MAP1.getColumns(); j++) {
                if (MAP1.getSingleElement(i, j) == C) {
                    Crates.add(new Crate(i, j, false));
                }
            }
        }

        /*
        for (Crate crate : Crates){
            System.out.println(" Row "+crate.getRowPos()+" COL:"+ crate.colPos);
        }

         */

        System.out.println(MAP1);

        JFrame frame = new JFrame("Key Listener");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(200,200));

        Container contentPane = frame.getContentPane();
        Player finalPlayer = Player1;
        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
                printEventInfo( event);
            }

            @Override
            public void keyReleased(KeyEvent event) {
            }

            @Override
            public void keyTyped(KeyEvent event) {
            }

            private void printEventInfo( KeyEvent e) {

                clearScreen();
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        MAP1.MoveRight(finalPlayer,MAP1,Crates);
                        break;
                    case KeyEvent.VK_LEFT:
                        MAP1.MoveLeft(finalPlayer,MAP1,Crates);
                        break;
                    case KeyEvent.VK_DOWN:
                        MAP1.MoveDown(finalPlayer,MAP1,Crates);
                        break;
                    case KeyEvent.VK_UP:
                        MAP1.MoveUp(finalPlayer,MAP1,Crates);
                        break;

                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(MAP1);



            }


            /*

            private String keyboardLocation(int keybrd) {
                switch (keybrd) {
                    case KeyEvent.VK_RIGHT:
                        MAP1.MoveRight(finalPlayer,MAP1,Crates);
                        System.out.println("reecchts");
                        return "Right";
                    case KeyEvent.VK_LEFT:
                        return "Left";
                    case KeyEvent.KEY_LOCATION_NUMPAD:
                        return "NumPad";
                    case KeyEvent.KEY_LOCATION_STANDARD:
                        return "Standard";
                    case KeyEvent.KEY_LOCATION_UNKNOWN:
                    default:
                        return "Unknown";
                }
            }
            */
        };
        // Create stuff
        JTextField textField = new JTextField();
        textField.addKeyListener(listener);
        contentPane.add(textField, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}


