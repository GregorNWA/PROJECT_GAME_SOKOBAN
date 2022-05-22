import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class Controller {
    private Map MAP1;
    private View View;
    public Controller(Map MAP1, View View) {
        initMap();
        this.MAP1 = MAP1;
        this.View = View;

    }

    public void initMap(){
        MAP1.initializeMap();
        MAP1.initializePlayer(MAP1);
        MAP1.initializeCrates(MAP1);
    }
     public void initView() {View.graphics();}
    static int win =0;



    //KeyListener listener = new KeyListener() {

        public void directionInput(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    //move right --> MAP
                    //Update Console and Graphs = View
                    MAP1.MoveRight(MAP1);

                    break;
                case KeyEvent.VK_LEFT:
                    MAP1.MoveLeft(MAP1);
                    break;
                case KeyEvent.VK_DOWN:
                    MAP1.MoveDown(MAP1);
                    break;
                case KeyEvent.VK_UP:
                    MAP1.MoveUp(MAP1);
                    break;
            }
            //


            //-------Victory

            /*if(MAP1.checkForVictory()) {

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
                    /*MAP1.setElements(Level_2);

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


                    for (Crate crate : Crates){
                        System.out.println(crate +"On mark : " +crate.getIsOnMark());
                    }*/
            //-----end victory system
            System.out.println(MAP1); //Move to View

        }
    }

//}
