public class Runner {
    public static void main(String[] args) {

        //automatic matrix size recognition
        Map MAP = new Map(Map.lvl1rows,Map.lvl1cols);
        MAP.initializeMap(MAP);

        //Views
        System.out.println("PRINT 1:. "+MAP);
        View view = new View(MAP);
        ViewConsole vc = new ViewConsole(MAP);

        Controller control = new Controller(MAP,view);
        control.initMap(MAP);
        control.initView();
        //Add views
        control.addObserver(view);
        control.addObserver(vc);
        view.printMAPV();

    }
}