public class Runner {
    public static void main(String[] args) {

        //Map creation
        Map MAP = new Map(Map.lvl1rows, Map.lvl1cols);
        MAP.initializeMap(MAP);

        //Views initialization
        View view = new View(MAP);
        ViewConsole vc = new ViewConsole(MAP);

        //Controller initialization
        Controller control = new Controller(MAP, view);
        control.initMap(MAP);
        control.initView();

        //Add views by adding the corresponding observers
        control.addObserver(view);
        control.addObserver(vc);

    }
}