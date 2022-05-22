import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class Controller {
    private static Map mapC;
    private final View View;
    int count=0;

    public Controller(Map m, View v) {
        //initMap();
        mapC = m;
        View = v;
        System.out.println("Print MapC: "+ mapC+count);
        count++;
    }

    public void initMap(Map map){
        map.initializePlayer(map);
        map.initializeCrates(map);
        mapC=map;
    }
     public void initView() {View.graphics();}
    static int win =0;

    //KeyListener listener = new KeyListener() {

        public static void directionInput(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    //move right --> MAP
                    //Update Console and Graphs = View
                    mapC.MoveRight(mapC);
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    mapC.MoveLeft();
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    mapC.MoveDown(mapC);
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    mapC.MoveUp(mapC);
                    break;
            }


            //


            //-------Victory

            /*if(mapC.checkForVictory()) {

            }

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
                    /*mapC.setElements(Level_2);

                    for (int i = 0; i < mapC.getRows(); i++) {
                        for (int j = 0; j < mapC.getColumns(); j++) {
                            if (mapC.getSingleElement(i, j) == P) {
                                Player1[0] = new Player(i, j, false);
                            }
                        }
                    }
                    Crates.clear(); //don't remember what it does, but useful
                    //Creating new Crates depending on the "C" in the Matrix
                    for (int i = 0; i < mapC.getRows(); i++) {
                        for (int j = 0; j < mapC.getColumns(); j++) {
                            if (mapC.getSingleElement(i, j) == C) {
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
                    }*/
            //-----end victory system

        }
    }

//}
