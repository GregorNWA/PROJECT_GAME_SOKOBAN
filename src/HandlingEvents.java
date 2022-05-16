import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class HandlingEvents implements Runnable {
//TIMoTHées sucks ELi smal cock nice cock
    //THIS IS INITIAL MAP SETUP
static JFrame frame;
    static int myX = 400;
    static boolean movedown;
    static int myY = 400;
    static Canvas canvas;
    static BufferStrategy bufferStrategy;
    boolean running = true;

    private String[][] elements;
    private int rows;
    private int columns;

    static final String S = "Stone";
    static final String P = "Player";
    static final String G = "Grass";
    static final String T = "Test";
    static final String C = "Crate";
    static final String M = "Mark";
    static final String MC = "mCrate";

    public HandlingEvents() {
        frame = new JFrame("Basic Game");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setLayout(null);
        canvas = new Canvas();
        canvas.setBounds(0, 0, 500, 500);
        canvas.setIgnoreRepaint(true);
        panel.add(canvas);
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                moveIt(evt);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
    }
    public void run() {
        while (running = true) {
            Paint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {

        HandlingEvents ex = new HandlingEvents();
        new Thread(ex).start();


        frame = new JFrame("Basic Game");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setLayout(null);
        canvas = new Canvas();
        canvas.setBounds(0, 0, 500, 500);
        canvas.setIgnoreRepaint(true);
        panel.add(canvas);
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                moveIt(evt);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();

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
                    {S,S,S,S,C,S},//0
                    {G,G,C,G,C,S},//1
                    {P,C,M,M,G,G},//2
                    {S,G,G,G,S,S},//3
                    {S,G,G,G,S,S},//4
                    {S,S,G,S,S,G},//5
            };     //0,1,2,3,4,5

            Map MAP1 = new Map(6,6);
            MAP1.setElements(MAP);

            Player Player1 = new Player(0,2, false);

            List<Crate> Crates = new ArrayList<Crate>();
            Crates.add(new Crate(1,4,false));
            Crates.add(new Crate(0,4,false));
            Crates.add(new Crate(2,1,false));

        /*//Find Crate out of List
        for (Crate crate : Crates) {
            if (crate.getColPos() == 1) {
                crate.IsOnMark=true;
            }
        }*/
            System.out.println(MAP1);
            //Up Click
            //Find Crate
            //MAP1.MoveUp(Player1, MAP1, Crates);
            //MAP1.MoveDown(Player1, MAP1, Crates);
            MAP1.MoveRight(Player1, MAP1, Crates);
            System.out.println(MAP1);
            //MAP1.MoveUp(Player1, MAP1, Crates);
            //MAP1.MoveDown(Player1, MAP1, Crates);
            MAP1.MoveRight(Player1, MAP1, Crates);
            System.out.println(MAP1);
            //MAP1.MoveUp(Player1, MAP1, Crates);
            //MAP1.MoveDown(Player1, MAP1, Crates);
            MAP1.MoveRight(Player1, MAP1, Crates);
            System.out.println(MAP1);
            //MAP1.MoveUp(Player1, MAP1, Crates);
            //MAP1.MoveDown(Player1, MAP1, Crates);
            MAP1.MoveRight(Player1, MAP1, Crates);
            System.out.println(MAP1);



            if(movedown ==true){
                MAP1.MoveDown(Player1, MAP1, Crates);
            }
            //Change Player Position

    }
    public void Paint() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, 500, 500);
        Paint(g);
        bufferStrategy.show();
    }

    protected void Paint(Graphics2D g) {
        g.fillOval(myX, myY, 50, 70);
    }
    public static void moveIt(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                myY += 32;
                movedown = true;
                break;
            case KeyEvent.VK_UP:
                myY -= 32;
                break;
            case KeyEvent.VK_LEFT:
                myX -= 32;
                break;
            case KeyEvent.VK_RIGHT:
                myX += 32;
                break;
        }

    }
}