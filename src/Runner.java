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
        Map MAP = new Map(Map.lvl1rows,Map.lvl1cols);
        View view = new View(MAP);
        Controller control = new Controller(MAP,view);
        //control.directionInput(new KeyEvent(e));
        control.initView();
        System.out.println(MAP);



    }

}