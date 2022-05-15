import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;

public class Map {

    private String[][] elements;
    private int rows;
    private int columns;

    public Map(int new_rows, int new_columns){
    this.elements = new String[new_rows][new_columns];
    this.rows = new_rows;
    this.columns = new_columns;
    }
    static final String S = "Stone";
    static final String P = "Player";
    static final String G = "Grass";
    static final String T = "Test";
    static final String C = "Crate";
    static final String M = "Mark";
    static final String MC = "mCrate";

    //Getters
    public int getColumns() {
        return columns;
    }
    public int getRows() {
        return rows;
    }

    public String getElements(int rows, int columns) {
        return elements[rows][columns];
    }

    //Setters
    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setElements(String[][] elements) {
        this.elements = elements;
    }
    public void setSingleElement(int row, int column, String tile) {
        if (row >= 0 && column >= 0 && row < rows && column < columns) {
            elements[row][column] = tile;
        }
    }

    public String getSingleElement(int row, int column) {
        if (row >= 0 && column >= 0 && row < rows && column < columns)
        return elements[row][column];
        return null;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString(){
        String result="";
        for (int i=0; i<rows;i++) {
            for (int j=0; j<columns;j++) {
                    result += this.getElements(i, j) + " ";
            }
            result +="\n";
        }
        result +="\n";
        return result;
    }

    public static void main(String[] args) {
        //Note: Add new Player with Position from Matrix, not manual positions)
        String[][] MAP = {
                //0,1,2,3,4,5
                {S, S, C, S, S, C},//0
                {G, C, G, G, C, S},//1
                {G, G, M, G, G, S},//2
                {S, G, M, G, S, S},//3
                {S, G, C, G, S, C},//4
                {S, S, P, S, S, G},//5
        };     //0,1,2,3,4,5

        Map MAP1 = new Map(6, 6);
        MAP1.setElements(MAP);

        //Creating new Player depending on "P" in Matrix
        Player Player1 = null;
        for (int i = 0; i < MAP1.getRows(); i++) {
            for (int j = 0; j < MAP1.getColumns(); j++) {
                if (MAP1.getSingleElement(i, j) == P) {
                    Player1 = new Player(i, j, false);
                }
            }
        }
        System.out.println("Player Position"+Player1.getRowPos()+","+Player1.getColPos());

        //Createing new Crates depending on the "C" in the Matrix
        List<Crate> Crates = new ArrayList<Crate>();
        for (int i = 0; i < MAP1.getRows(); i++) {
            for (int j = 0; j < MAP1.getColumns(); j++) {
                if (MAP1.getSingleElement(i, j) == C) {
                    Crates.add(new Crate(i, j, false));
                }
            }
        }

        for (Crate crate : Crates){
            System.out.println(" Row "+crate.getRowPos()+" COL:"+ crate.colPos);
        }

        System.out.println(MAP1);
        //Up Click
        //Find Crate
        MAP1.MoveUp(Player1, MAP1, Crates);

        System.out.println(MAP1);
        MAP1.MoveUp(Player1, MAP1, Crates);
        System.out.println(MAP1);
        MAP1.MoveUp(Player1, MAP1, Crates);
        System.out.println(MAP1);
        MAP1.MoveUp(Player1, MAP1, Crates);
        System.out.println(MAP1);

        //Change Player Position
    }
    public void MoveUp(Player PlayUp, Map Map, List<Crate> Crates)  {

        int r;
        int c;
        r = PlayUp.getRowPos();
        c = PlayUp.getColPos();

        //Check what the next tile is
        switch (Map.getSingleElement(r - 1, c)) {
            //if next Tile is a Mark:
            case M:
                break;
            //If next Tile is Stone we don't move:
            case S:
                System.out.println("Stone ahead!");
                break;
            //If next Tile is Grass we move
            case G:
                Map.setSingleElement(r, c, G);
                PlayUp.setRowPos(r - 1);
                Map.setSingleElement(r - 1, c, P);
                break;
            //If next tile is a Crate..it gets difficult:
            case C:
                //If the tile after the Crate is a Stone we don't move. Also our Player cannot move a Crate behind a Crate
                if (Map.getSingleElement(r - 2, c) == S || Map.getSingleElement(r - 2, c) == C) {
                    System.out.println("Stone behind Crate!");
                //If not we move
                } else {
                    //Find the Crate out of List of Crates
                    for (Crate crate : Crates) {
                        if (crate.getRowPos() == (r - 1) && crate.getColPos() == c) {
                            boolean In2TilesMark=false;
                            if (Map.getSingleElement(r - 2, c) == M){
                                In2TilesMark =true;}
                            //transferring information about marks on the ground
                            if (crate.IsOnMark == true) {
                                Map.setSingleElement((r - 2), c, C); //Push Crate 1 TIle in Matrix
                                crate.setRowPos(r - 2);     //Update Position of the Crate Object
                                if (PlayUp.getIsOnMark() == true) {
                                    Map.setSingleElement(r, c, M);
                                } else {
                                    Map.setSingleElement(r, c, G);
                                }
                                //Moving the Player
                                PlayUp.setRowPos(r - 1); //Update Player's Row
                                Map.setSingleElement(r - 1, c, P); //Update PLayer in Matrix

                                PlayUp.setIsOnMark(true); //If Crate was on Mark, Player will be On Mark
                            } else {
                                Map.setSingleElement((r - 2), c, C); //Push Crate 1 TIle in Matrix
                                crate.setRowPos(r - 2);     //Update Position of the Crate Object
                                if (PlayUp.getIsOnMark() == true) {Map.setSingleElement(r, c, M);
                                } else {Map.setSingleElement(r, c, G);
                                }
                                PlayUp.setRowPos(r - 1); //Update Player's Row
                                Map.setSingleElement(r - 1, c, P); //Update PLayer in Matrix

                                crate.setRowPos(r - 2);
                                Map.setSingleElement((r - 2), c, C);
                            }
                            if (In2TilesMark==true) {
                                crate.setIsOnMark(true);
                            }
                            }
                        }
                    }
                    break;
            default:
                System.out.println("No Case");
        }
    }
}
