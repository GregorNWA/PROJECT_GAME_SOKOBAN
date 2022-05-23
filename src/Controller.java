import java.awt.event.KeyEvent;

public class Controller {
    static int count = 1;
    private static Map mapC;
    private static View View;

    public Controller(Map m, View v) {
        mapC = m;
        View = v;
    }

    public static void directionInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                mapC.MoveRight();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                mapC.MoveLeft();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                mapC.MoveDown();
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                mapC.MoveUp();
                break;
        }

        switch (mapC.checkLevel()) {
            case 2: {
                if (count == 0) {
                    System.out.println("You won Level 1! Congratulations!");
                    System.out.println("Time for Level 2!");
                    count = 1;
                }
                break;
            }
            case 3: {
                if (count == 1) {
                    System.out.println("You won Level 2! Congratulations!");
                    System.out.println("That means you won the Game!");
                    count = 2;
                    View.closeWindow();
                    break;
                }
            }
        }
    }

    public void initMap(Map map) {
        map.initializePlayer(map);
        map.initializeCrates(map);
        mapC = map;
    }

    public void initView() {
        View.graphics();
    }
}
