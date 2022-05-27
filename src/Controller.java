import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static int count = 1;
    private  Map mapC;
    private static View View;

    private static final List<ViewObserver> observers = new ArrayList<>();
    public Controller(Map m, View v) {
        mapC = m;
        View = v;
    }

    public void addObserver(ViewObserver viewObs){
        observers.add(viewObs);
    }

    public void directionInput(String direction) {
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

    public void setController(Controller c){
        c.mapC=mapC;
        c.View= View;
    }

    public  void updateObservers(){
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
    public  void checker() {
        switch (mapC.checkLevel()) {
            //Souts should be handeled by ViewConsole
            case 1 -> {
                if (count == 0) {
                    System.out.println("Level 1 clear\n");
                    System.out.println("You won Level 1! Congratulations!");
                    System.out.println("Time for Level 2!");
                    count = 1;
                }
            }
            case 3 -> {
                if (count == 1) {
                    System.out.println("You won Level 2! Congratulations!");
                    System.out.println("That means you won the Game!");
                    System.out.println("" +
                            "┌───────────────────────────────────┐\n" +
                            "│                                   │\n" +
                            "│  WW   W   W     W     W  W   W    │\n" +
                            "│   WW WWW WW     W     WW W   W    │\n" +
                            "│    WWW WWW      W     W WW        │\n" +
                            "│     WW  W       W     W  W   @    │\n" +
                            "│                                   │\n" +
                            "└───────────────────────────────────┘");
                    count = 2;
                    View.closeWindow();
                }
            }
        }
    }
}
