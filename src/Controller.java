import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    static int count = 1;
    private static Map mapC;
    private static View View;

    private static List<ViewObserver> observers;

    public Controller(Map m, View v) {
        ViewConsole vc=new ViewConsole(m);
        mapC = m;
        View = v;
        observers=new ArrayList<>();
        observers.add(View);
        observers.add(vc);

    }

    public static void directionInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> mapC.MoveRight();
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> mapC.MoveLeft();
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> mapC.MoveDown();
            case KeyEvent.VK_UP, KeyEvent.VK_W -> mapC.MoveUp();
        }
        checker();
        updateObservers();
    }
    public static void ButtonInput(String direction) {
        switch (direction) {
            case "LEFT" -> {
                mapC.MoveLeft();
            }
            case "RIGHT" -> mapC.MoveRight();
            case "DOWN" -> mapC.MoveDown();
            case "UP" -> mapC.MoveUp();
        }
        checker();
        updateObservers();
    }

    public static void checker() {
        switch (mapC.checkLevel()) {
            //Sout could be handeled my View
            case 2 -> {
                if (count == 0) {
                    System.out.println("You won Level 1! Congratulations!");
                    System.out.println("Time for Level 2!");
                    count = 1;
                }
            }
            case 3 -> {
                if (count == 1) {
                    System.out.println("You won Level 2! Congratulations!");
                    System.out.println("That means you won the Game!");
                    count = 2;
                    View.closeWindow();
                }
            }
        }
    }

    public static void updateObservers(){
        for(ViewObserver v: observers){
            v.updateMap();
        }
    }

    public void initMap(Map map) {
        map.initializePlayer(map);
        map.initializeCrates(map);
        mapC = map;
    }

    public void initView() {
        View.graphics();
        View.addButtons();
    }
}
