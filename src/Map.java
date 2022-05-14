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

/*
    public int PlayerPosition(){
        for (int i=0; i<rows;i++) {
            for (int j=0; j<columns;j++) {
                if (this.getElements(i,j)=="Player"){
                    getRows();
                }
            }
            return rows;
        }
    }   */

    public void MoveUp(Player PlayUp, Map Map, Crate Cratx)  {

        int r;
        int c;
        r = PlayUp.getRowPos();
        c = PlayUp.getColPos();


        switch (Map.getSingleElement(r-1, c)) {
            case S:
                System.out.println("Stone ahead!");
                break;
            case G:
                Map.setSingleElement(r, c, G);
                PlayUp.setRowPos(r - 1);
                r = PlayUp.getRowPos();
                c = PlayUp.getColPos();
                Map.setSingleElement(r, c, P);
                break;
            case C:
                if (Map.getSingleElement(r - 2, c) == S) {
                    System.out.println("Stone behind Crate!");
                } else {
                    if (Map.getSingleElement(r - 1, c) ==M) {
                        Cratx.setIsOnMark(true);
                    }


                    Map.setSingleElement(r, c, G);
                    PlayUp.setRowPos(r - 1);
                    r = PlayUp.getRowPos();
                    c = PlayUp.getColPos();
                    Map.setSingleElement(r, c, P);


                    //Playermovement
                    Map.setSingleElement(r, c, G);
                    PlayUp.setRowPos(r - 1);
                    r = PlayUp.getRowPos();
                    c = PlayUp.getColPos();
                    Map.setSingleElement(r, c, P);

                    break;
                }
                break;
        }
    }


    public static void main(String[] args) {


        //Note: Add new Player with Position from Matrix, not manual positions)
        String[][] MAP = {
                {S,S,S,S,S,S},
                {G,G,M,G,G,S},
                {G,G,G,G,G,S},
                {S,G,C,G,S,S},
                {S,G,G,G,S,S},
                {S,S,P,S,S,G},
        };

        Map MAP1 = new Map(6,6);
        MAP1.setElements(MAP);

        Player Player1 = new Player(5,2, false);
        Crate Crate1 = new Crate(3,2,false);

        Crate Crate2 = new Crate(3,2,false);
        System.out.println(MAP1);
        System.out.println(Player1.getRowPos());
        System.out.println(Player1.getColPos());
        MAP1.MoveUp(Player1,MAP1,Crate1);
        System.out.println(Player1.getRowPos());
        System.out.println(Player1.getColPos());

        System.out.println(MAP1);

        //Change Player Position

    }
}
