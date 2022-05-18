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