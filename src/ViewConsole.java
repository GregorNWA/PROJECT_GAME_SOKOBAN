public class ViewConsole implements ViewObserver {
    private Map map;
    public ViewConsole(Map map){
        this.map=map;
        updateMap();
    }
    @Override
    public void updateMap() {
        System.out.println(map);
    }
}
