public class ViewConsole implements ViewObserver {
    private Map map;
    public ViewConsole(Map map){
        this.map=map;
    }
    @Override
    public void updateMap() {
        System.out.println(map);
    }
}
