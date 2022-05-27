import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static int count = 1; //variable for the winning system
    private static final List<ViewObserver> observers = new ArrayList<>();

    //Controller's arguments
    private  Map mapC;
    private static View View;

    public Controller(Map m, View v) {
        mapC = m;
        View = v;
    }

    //Method to add the observers in an ArrayList later on
    public void addObserver(ViewObserver viewObs){
        observers.add(viewObs);
    }

    //Depending on the direction received in input, we will move to this direction
    public void directionInput(String direction) {
        switch (direction) {
            case "LEFT" -> mapC.MoveLeft();
            case "RIGHT" -> mapC.MoveRight();
            case "DOWN" -> mapC.MoveDown();
            case "UP" -> mapC.MoveUp();
        }
        checker();
        updateObservers();
    }

    //Updates the observers to update the whole map
    public void updateObservers(){
        for(ViewObserver v: observers){
            v.updateMap();
        }
    }

    //Initializes the map
    public void initMap(Map map) {
        map.initializePlayer(map);
        map.initializeCrates(map);
        mapC = map;
    }

    public void initView() {
        View.graphics();
        View.addButtons();
    }

    //Method taking care of printing the win if the winning conditions are meet
    public  void checker() {
        switch (mapC.checkLevel()) { //Check if the winning conditions are fulfilled
            case 1 -> {
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
