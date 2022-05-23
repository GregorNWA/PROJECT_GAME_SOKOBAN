public class Runner {
    public static void main(String[] args) {

        //automatic matrix size recognition
        Map MAP = new Map(Map.lvl1rows,Map.lvl1cols);
        MAP.initializeMap(MAP);
        View view = new View(MAP);
        Controller control = new Controller(MAP,view);
        System.out.println(MAP);
        //.directionInput();
        control.initMap(MAP);
        control.initView();
        //System.out.println(MAP);
    }
}