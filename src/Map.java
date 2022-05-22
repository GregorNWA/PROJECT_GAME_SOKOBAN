import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;

public class Map {

    private String[][] elements;
    private int rows;
    private int columns;

    private Map mapM;
    private View View;

    Player Player;
    List<Crate> Crates;

    public Map(int new_rows, int new_columns) {
        this.elements = new String[new_rows][new_columns];
        this.rows = new_rows;
        this.columns = new_columns;
        //initializeMap(map);
    }

    static final String S  = "  ▀▀  ";
    static final String P  = "  P1  ";
    static final String G  = "  --  ";
    static final String T  = "  TT  ";
    static final String C  = "  []  ";
    static final String M  = "  ()  ";
    static final String MC = "  [.]  ";

    static String[][] Level_1 = {

            //0, 1, 2, 3, 4, 5, 6, 7, 8
            {S, S, S, S, S, S, S, S, G},//0
            {S, G, G, S, G, G, G, S, G},//1
            {S, G, G, G, C, G, G, S, G},//2
            {S, G, G, S, C, C, G, S, G},//3
            {S, S, G, S, G, G, G, S, G},//4
            {S, M, M, G, C, S, G, S, G},//5
            {S, M, M, G, G, G, P, S, G},//6
            {S, S, S, S, S, S, S, S, G},//7
            {G, G, G, G, G, G, G, G, G},//8
            //0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    //Map level 2
    static String[][] Level_2 = {
            //0, 1, 2, 3, 4, 5, 6, 7, 8
            {S, S, S, S, S, S, S, S, S},//0
            {S, M, M, G, C, G, M, M, S},//1
            {S, M, M, C, C, G, M, M, S},//2
            {S, G, G, C, G, C, C, G, S},//3
            {S, C, C, G, S, G, C, C, S},//4
            {S, G, C, C, G, C, G, G, S},//5
            {S, M, M, G, C, C, M, M, S},//6
            {S, M, M, G, C, P, M, M, S},//7
            {S, S, S, S, S, S, S, S, S},//8
            //0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    //automatic matrix size recognition
    static int lvl1rows = Level_1.length;
    static int lvl1cols = Level_1.length;
    static int lvl2rows = Level_2.length;
    static int lvl2cols = Level_2.length;


    public static String[][] getLevel_1() {
        return Level_1;
    }

    public static String[][] getLevel_2() {
        return Level_2;
    }


    //Initialize Map based on Matrix
    public void initializeMap(Map map) {
        map.setElements(Level_1);
        //return map;
    }

    public void initializePlayer(Map mapM) {
        final Player[] Player1 = {null};
        for (int i = 0; i < mapM.getRows(); i++) {
            for (int j = 0; j < mapM.getColumns(); j++) {
                if (mapM.getSingleElement(i, j) == P) {
                    Player1[0] = new Player(i, j, false);
                }
            }
        }
    }

    //Same for the crates
    public List<Crate> initializeCrates(Map mapM) {
        //Make sure to clear List for 2. Lvl
        Crates = new ArrayList<Crate>();
        //Creating new Crates depending on the "C" in the Matrix
        for (int i = 0; i < mapM.getRows(); i++) {
            for (int j = 0; j < mapM.getColumns(); j++) {
                if (mapM.getSingleElement(i, j) == C) {
                    Crates.add(new Crate(i, j, false));
                }
            }
        }
        System.out.println("Crates in Method: "+ Crates);
        return Crates;
    }

    public void printCrates (){
        System.out.println("Crates outside: "+Crates);
    }

    public List<Crate> getCrates(){
        return Crates;
    }

    public void clearCRATES(List<Crate> Crates) {
        Crates.clear();
    }



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
    public String toString() {
        String result = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result += this.getElements(i, j) + " ";
            }
            result += "\n";
        }
        result += "\n";
        return result;
    }




    public void MoveUp(Map Map) {

        int r;
        int c;
        r = Player.getRowPos();
        c = Player.getColPos();

        //Check what the next tile is
        switch (Map.getSingleElement(r - 1, c)) {
            //if next Tile is a Mark:
            case M:
                if (Player.IsOnMark){
                    Map.setSingleElement(r,c,M);
                }else {
                    Map.setSingleElement(r, c, G);
                }
                Player.setRowPos(r - 1);
                Map.setSingleElement(r - 1, c, P);
                Player.setIsOnMark(true);
                break;
            //If next Tile is Stone we don't move:
            case S:
                System.out.println("Stone ahead!");
                break;
            //If next Tile is Grass we move
            case G:
                if(Player.IsOnMark){
                    Map.setSingleElement(r,c,M);
                } else {
                    Map.setSingleElement(r, c, G);
                }
                Player.setIsOnMark(false);
                Player.setRowPos(r - 1);
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
                            boolean In2TilesMark = false;
                            if (Map.getSingleElement(r - 2, c) == M) {
                                In2TilesMark = true;
                            }
                            //transferring information about marks on the ground
                            if (crate.IsOnMark == true) {
                                Map.setSingleElement((r - 2), c, C); //Push Crate 1 TIle in Matrix
                                crate.setRowPos(r - 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark() == true) {
                                    Map.setSingleElement(r, c, M);
                                } else {
                                    Map.setSingleElement(r, c, G);
                                }
                                //Moving the Player
                                Player.setRowPos(r - 1); //Update Player's Row
                                Map.setSingleElement(r - 1, c, P); //Update PLayer in Matrix

                                Player.setIsOnMark(true); //If Crate was on Mark, Player will be On Mark
                                crate.setIsOnMark(false);
                            } else {
                                Map.setSingleElement((r - 2), c, C); //Push Crate 1 TIle in Matrix
                                crate.setRowPos(r - 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark() == true) {
                                    Map.setSingleElement(r, c, M);
                                } else {
                                    Map.setSingleElement(r, c, G);
                                }
                                Player.setRowPos(r - 1); //Update Player's Row
                                Map.setSingleElement(r - 1, c, P); //Update PLayer in Matrix

                                crate.setRowPos(r - 2);
                                Map.setSingleElement((r - 2), c, C);
                                Player.setIsOnMark(false); //If Crate was on Mark, Player will be NOT On Mark
                            }
                            if (In2TilesMark == true) {
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


    public void MoveDown(Map Map) {

        int r;
        int c;
        r = Player.getRowPos();
        c = Player.getColPos();

        //Check what the next tile is
        switch (Map.getSingleElement(r + 1, c)) {
            //if next Tile is a Mark:
            case M:
                if (Player.IsOnMark){
                    Map.setSingleElement(r,c,M);
                }else {
                    Map.setSingleElement(r, c, G);
                }
                Player.setRowPos(r + 1);
                Map.setSingleElement(r + 1, c, P);
                Player.setIsOnMark(true);
                break;
            //If next Tile is Stone we don't move:
            case S:
                System.out.println("Stone ahead!");
                break;
            //If next Tile is Grass we move
            case G:
                if(Player.IsOnMark){
                    Map.setSingleElement(r,c,M);
                } else {
                    Map.setSingleElement(r, c, G);
                }
                Player.setIsOnMark(false);
                Player.setRowPos(r + 1);
                Map.setSingleElement(r + 1, c, P);
                break;
            //If next tile is a Crate..it gets difficult:
            case C:
                //If the tile after the Crate is a Stone we don't move. Also our Player cannot move a Crate behind a Crate
                if (Map.getSingleElement(r + 2, c) == S || Map.getSingleElement(r + 2, c) == C) {
                    System.out.println("Stone behind Crate!");
                    //If not we move
                } else {
                    //Find the Crate out of List of Crates
                    for (Crate crate : Crates) {
                        if (crate.getRowPos() == (r + 1) && crate.getColPos() == c) {
                            boolean In2TilesMark = false;
                            if (Map.getSingleElement(r + 2, c) == M) {
                                In2TilesMark = true;
                            }
                            //transferring information about marks on the ground
                            if (crate.IsOnMark == true) {
                                Map.setSingleElement((r + 2), c, C); //Push Crate 1 TIle in Matrix
                                crate.setRowPos(r + 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark() == true) {
                                    Map.setSingleElement(r, c, M);
                                } else {
                                    Map.setSingleElement(r, c, G);
                                }
                                //Moving the Player
                                Player.setRowPos(r + 1); //Update Player's Row
                                Map.setSingleElement(r + 1, c, P); //Update PLayer in Matrix

                                Player.setIsOnMark(true); //If Crate was on Mark, Player will be On Mark
                                crate.setIsOnMark(false);
                            } else {
                                Map.setSingleElement((r + 2), c, C); //Push Crate 1 TIle in Matrix
                                crate.setRowPos(r + 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark() == true) {
                                    Map.setSingleElement(r, c, M);
                                } else {
                                    Map.setSingleElement(r, c, G);
                                }
                                Player.setRowPos(r + 1); //Update Player's Row
                                Map.setSingleElement(r + 1, c, P); //Update PLayer in Matrix

                                crate.setRowPos(r + 2);
                                Map.setSingleElement((r + 2), c, C);
                                Player.setIsOnMark(false); //If Crate was on Mark, Player will be NOT On Mark
                            }
                            if (In2TilesMark == true) {
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


    public void MoveRight(Map Map) {

        int r;
        int c;
        r = Player.getRowPos();
        c = Player.getColPos();

        //Check what the next tile is
        switch (Map.getSingleElement(r, c + 1)) {
            //if next Tile is a Mark:
            case M:
                if (Player.IsOnMark){
                    Map.setSingleElement(r,c,M);
                }else {
                    Map.setSingleElement(r, c, G);
                }
                Player.setColPos(c + 1);
                Map.setSingleElement(r, c+1, P);
                Player.setIsOnMark(true);
                break;
            //If next Tile is Stone we don't move:
            case S:
                System.out.println("Stone ahead!");
                break;
            //If next Tile is Grass we move
            case G:
                if(Player.IsOnMark){
                    Map.setSingleElement(r,c,M);
                } else {
                    Map.setSingleElement(r, c, G);
                }
                Player.setIsOnMark(false);
                Player.setColPos(c+ 1);
                Map.setSingleElement(r, c+1, P);
                break;
            //If next tile is a Crate..it gets difficult:
            case C:
                //If the tile after the Crate is a Stone we don't move. Also our Player cannot move a Crate behind a Crate
                if (Map.getSingleElement(r, c + 2) == S || Map.getSingleElement(r, c + 2) == C) {
                    System.out.println("Stone behind Crate!");
                    //If not we move
                } else {
                    //Find the Crate out of List of Crates
                    for (Crate crate : Crates) {
                        if (crate.getColPos() == (c + 1) && crate.getRowPos() == r) {
                            boolean In2TilesMark = false;
                            if (Map.getSingleElement(r, c + 2) == M) {
                                In2TilesMark = true;
                            }
                            //transferring information about marks on the ground
                            if (crate.IsOnMark == true) {
                                Map.setSingleElement(r, c + 2, C); //Push Crate 1 TIle in Matrix
                                crate.setColPos(c + 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark() == true) {
                                    Map.setSingleElement(r, c, M);
                                } else {
                                    Map.setSingleElement(r, c, G);
                                }
                                //Moving the Player
                                Player.setColPos(c + 1); //Update Player's Row
                                Map.setSingleElement(r, c + 1, P); //Update PLayer in Matrix

                                Player.setIsOnMark(true); //If Crate was on Mark, Player will be On Mark
                                crate.setIsOnMark(false);
                            } else {
                                Map.setSingleElement(r, c + 2, C); //Push Crate 1 TIle in Matrix
                                crate.setColPos(c + 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark() == true) {
                                    Map.setSingleElement(r, c, M);
                                } else {
                                    Map.setSingleElement(r, c, G);
                                }
                                Player.setColPos(c + 1); //Update Player's Row
                                Map.setSingleElement(r, c + 1, P); //Update PLayer in Matrix

                                crate.setColPos(c + 2);
                                Map.setSingleElement(r, c + 2, C);
                                Player.setIsOnMark(false); //If Crate was on Mark, Player will be NOT On Mark
                            }
                            if (In2TilesMark == true) {
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


    public void MoveLeft(Map Map) {

        int r;
        int c;
        r = Player.getRowPos();
        c = Player.getColPos();

        //Check what the next tile is
        switch (Map.getSingleElement(r, c - 1)) {
            //if next Tile is a Mark:
            case M:
                if (Player.IsOnMark){
                    Map.setSingleElement(r,c,M);
                }else {
                    Map.setSingleElement(r, c, G);
                }
                Player.setColPos(c - 1);
                Map.setSingleElement(r, c-1, P);
                Player.setIsOnMark(true);
                break;
            //If next Tile is Stone we don't move:
            case S:
                System.out.println("Stone ahead!");
                break;
            //If next Tile is Grass we move
            case G:
                if(Player.IsOnMark){
                    Map.setSingleElement(r,c,M);
                } else {
                    Map.setSingleElement(r, c, G);
                }
                Player.setIsOnMark(false);
                Player.setColPos(c - 1);
                Map.setSingleElement(r, c-1, P);
                break;
            //If next tile is a Crate..it gets difficult:
            case C:
                //If the tile after the Crate is a Stone we don't move. Also our Player cannot move a Crate behind a Crate
                if (Map.getSingleElement(r, c - 2) == S || Map.getSingleElement(r, c - 2) == C) {
                    System.out.println("Stone behind Crate!");
                    //If not we move
                } else {
                    //Find the Crate out of List of Crates
                    for (Crate crate : Crates) {
                        if (crate.getColPos() == (c - 1) && crate.getRowPos() == r) {
                            boolean In2TilesMark = false;
                            if (Map.getSingleElement(r, c - 2) == M) {
                                In2TilesMark = true;
                            }
                            //transferring information about marks on the ground
                            if (crate.IsOnMark == true) {
                                Map.setSingleElement(r, c - 2, C); //Push Crate 1 TIle in Matrix
                                crate.setColPos(c - 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark() == true) {
                                    Map.setSingleElement(r, c, M);
                                } else {
                                    Map.setSingleElement(r, c, G);
                                }
                                //Moving the Player
                                Player.setColPos(c - 1); //Update Player's Row
                                Map.setSingleElement(r, c - 1, P); //Update PLayer in Matrix

                                Player.setIsOnMark(true); //If Crate was on Mark, Player will be On Mark
                                crate.setIsOnMark(false);
                            } else {
                                Map.setSingleElement(r, c - 2, C); //Push Crate 1 TIle in Matrix
                                crate.setColPos(c - 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark() == true) {
                                    Map.setSingleElement(r, c, M);
                                } else {
                                    Map.setSingleElement(r, c, G);
                                }
                                Player.setColPos(c - 1); //Update Player's Row
                                Map.setSingleElement(r, c - 1, P); //Update PLayer in Matrix

                                crate.setColPos(c - 2);
                                Map.setSingleElement(r, c - 2, C);
                                Player.setIsOnMark(false); //If Crate was on Mark, Player will be NOT On Mark
                            }
                            if (In2TilesMark == true) {
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