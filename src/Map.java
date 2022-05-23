import java.util.ArrayList;
import java.util.List;

public class Map {

    static final String S = "  ▀▀  ";
    static final String P = "  P1  ";
    static final String G = "  --  ";
    static final String C = "  []  ";
    static final String M = "  ()  ";
    //Norma Game
    static String[][] Level_1 = {

            //0, 1, 2, 3, 4, 5, 6, 7, 8
            {S, S, S, S, S, S, S, S, S},//0
            {S, G, G, S, G, G, G, G, S},//1
            {S, G, G, G, C, G, G, G, S},//2
            {S, G, G, S, C, C, G, G, S},//3
            {S, S, G, S, G, G, G, G, S},//4
            {S, M, M, G, C, S, G, G, S},//5
            {S, M, M, G, G, G, P, G, S},//6
            {S, G, G, S, G, G, G, G, S},//8
            {S, S, S, S, S, S, S, S, S},//7

            //0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    //automatic matrix size recognition
    static int lvl1rows = Level_1.length;
    static int lvl1cols = Level_1.length;
    //Map level 2
    static String[][] Level_2 = {
            //0, 1, 2, 3, 4, 5, 6, 7, 8
            {S, S, S, S, S, S, S, S, S},//0
            {S, M, M, G, C, G, M, M, S},//1
            {S, M, G, G, G, G, G, M, S},//2
            {S, C, C, G, G, G, C, G, S},//3
            {S, G, G, G, S, G, C, C, S},//4
            {S, C, C, G, G, G, G, G, S},//5
            {S, M, G, C, G, C, C, M, S},//6
            {S, M, M, G, C, P, M, M, S},//7
            {S, S, S, S, S, S, S, S, S},//8
            //0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    static int lvl2rows = Level_2.length;
    static int lvl2cols = Level_2.length;
    Player Player;
    List<Crate> Crates;
    int currLvl = 1;

    /*
    TEST MAPS:
    static String[][] Level_1 = {

            //0, 1, 2, 3, 4, 5, 6, 7, 8
            {S, S, S, S, S, S, S, S, G},//0
            {S, G, G, S, G, G, G, S, G},//1
            {S, G, G, G, G, G, G, S, G},//2
            {S, G, G, G, G, C, G, S, G},//3
            {S, S, G, G, G, G, G, S, G},//4
            {S, M, M, G, G, S, G, S, G},//5
            {S, M, C, G, G, G, P, S, G},//6
            {S, S, S, S, S, S, S, S, G},//7
            {G, G, G, G, G, G, G, G, G},//8
            //0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    //TEST level 2
    static String[][] Level_2 = {
        //0, 1, 2, 3, 4, 5, 6, 7, 8
        {S, S, S, S, S, S, S, S, S},//0
        {S, M, M, G, G, G, M, M, S},//1
        {S, M, M, G, C, G, M, M, S},//2
        {S, G, G, G, G, G, G, G, S},//3
        {S, G, G, G, S, G, G, G, S},//4
        {S, G, C, G, G, G, G, G, S},//5
        {S, M, M, G, G, G, M, M, S},//6
        {S, M, M, G, G, P, M, M, S},//7
        {S, S, S, S, S, S, S, S, S},//8
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
    };*/
    private String[][] elements;
    private int rows;
    private int columns;
    private Map mapM;
    public Map(int new_rows, int new_columns) {
        this.elements = new String[new_rows][new_columns];
        this.rows = new_rows;
        this.columns = new_columns;
    }
    //for later use
    public static String[][] getLevel_1() {
        return Level_1;
    }
    public static String[][] getLevel_2() {
        return Level_2;
    }


    //Initialize Map based on Matrix
    public void initializeMap(Map map) {
        map.setElements(Level_1);
        mapM = map;
    }

    public Player initializePlayer(Map mapM) {
        Player = null;
        for (int i = 0; i < mapM.getRows(); i++) {
            for (int j = 0; j < mapM.getColumns(); j++) {
                if (mapM.getSingleElement(i, j) == P) {
                    Player = new Player(i, j, false);
                }
            }
        }
        return Player;
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
        return Crates;
    }

    public List<Crate> getCrates() {
        return Crates;
    }

    public void clearCRATES(List<Crate> Crates) {
        Crates.clear();
    }

    public Map getMapM() {
        return mapM;
    }

    //Getters
    public int getColumns() {
        return columns;
    }

    //Setters
    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getElements(int rows, int columns) {
        return elements[rows][columns];
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

    public int checkLevel() {
        //-------Victory system
        int count = 0;
        for (Crate crate : Crates) {
            if (crate.IsOnMark) {
                count++;
            }
        }

        if ((count >= Crates.size())) {
            if (currLvl == 1) {
                //first level
                System.out.println("Level 1 clear\n");

                //load second Map: Maybe move to Controller
                mapM.setElements(Level_2);
                mapM.initializePlayer(mapM);
                Crates.clear();
                //Creating new Crates depending on the "C" in the Matrix
                mapM.initializeCrates(getMapM());
                count = 0;
                currLvl = 2;
                System.out.println("You won Level 1! Congratulations!");
                System.out.println("Time for Level 2!");
                return currLvl;
            }
            for (Crate crate : Crates) {
                if (crate.IsOnMark) {
                    count++;
                }
            }
            if (count >= Crates.size() && currLvl == 2) {
                currLvl = 3;
                return currLvl;
            }
            count = 0;
        }
        return currLvl;
    }

    public void MoveUp() {

        int r;
        int c;
        r = Player.getRowPos();
        c = Player.getColPos();

        //Check what the next tile is
        switch (mapM.getSingleElement(r - 1, c)) {
            //if next Tile is a Mark:
            case M:
                if (Player.IsOnMark) {
                    mapM.setSingleElement(r, c, M);
                } else {
                    mapM.setSingleElement(r, c, G);
                }
                Player.setRowPos(r - 1);
                mapM.setSingleElement(r - 1, c, P);
                Player.setIsOnMark(true);
                break;
            //If next Tile is Stone we don't move:
            case S:
                System.out.println("Stone ahead!");
                break;
            //If next Tile is Grass we move
            case G:
                if (Player.IsOnMark) {
                    mapM.setSingleElement(r, c, M);
                } else {
                    mapM.setSingleElement(r, c, G);
                }
                Player.setIsOnMark(false);
                Player.setRowPos(r - 1);
                mapM.setSingleElement(r - 1, c, P);
                break;
            //If next tile is a Crate..it gets difficult:
            case C:
                //If the tile after the Crate is a Stone we don't move. Also, our Player cannot move a Crate behind a Crate
                if (mapM.getSingleElement(r - 2, c) == S || mapM.getSingleElement(r - 2, c) == C) {
                    System.out.println("Stone behind Crate!");
                    //If not we move
                } else {
                    //Find the Crate out of List of Crates
                    for (Crate crate : Crates) {
                        if (crate.getRowPos() == (r - 1) && crate.getColPos() == c) {
                            boolean In2TilesMark = mapM.getSingleElement(r - 2, c) == M;
                            //transferring information about marks on the ground
                            if (crate.IsOnMark) {
                                mapM.setSingleElement((r - 2), c, C); //Push Crate 1 TIle in Matrix
                                crate.setRowPos(r - 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark()) {
                                    mapM.setSingleElement(r, c, M);
                                } else {
                                    mapM.setSingleElement(r, c, G);
                                }
                                //Moving the Player
                                Player.setRowPos(r - 1); //Update Player's Row
                                mapM.setSingleElement(r - 1, c, P); //Update PLayer in Matrix

                                Player.setIsOnMark(true); //If Crate was on Mark, Player will be On Mark
                                crate.setIsOnMark(false);
                            } else {
                                mapM.setSingleElement((r - 2), c, C); //Push Crate 1 TIle in Matrix
                                crate.setRowPos(r - 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark()) {
                                    mapM.setSingleElement(r, c, M);
                                } else {
                                    mapM.setSingleElement(r, c, G);
                                }
                                Player.setRowPos(r - 1); //Update Player's Row
                                mapM.setSingleElement(r - 1, c, P); //Update PLayer in Matrix

                                crate.setRowPos(r - 2);
                                mapM.setSingleElement((r - 2), c, C);
                                Player.setIsOnMark(false); //If Crate was on Mark, Player will be NOT On Mark
                            }
                            if (In2TilesMark) {
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

    public void MoveDown() {

        int r;
        int c;
        r = Player.getRowPos();
        c = Player.getColPos();

        //Check what the next tile is
        switch (mapM.getSingleElement(r + 1, c)) {
            //if next Tile is a Mark:
            case M:
                if (Player.IsOnMark) {
                    mapM.setSingleElement(r, c, M);
                } else {
                    mapM.setSingleElement(r, c, G);
                }
                Player.setRowPos(r + 1);
                mapM.setSingleElement(r + 1, c, P);
                Player.setIsOnMark(true);
                break;
            //If next Tile is Stone we don't move:
            case S:
                System.out.println("Stone ahead!");
                break;
            //If next Tile is Grass we move
            case G:
                if (Player.IsOnMark) {
                    mapM.setSingleElement(r, c, M);
                } else {
                    mapM.setSingleElement(r, c, G);
                }
                Player.setIsOnMark(false);
                Player.setRowPos(r + 1);
                mapM.setSingleElement(r + 1, c, P);
                break;
            //If next tile is a Crate..it gets difficult:
            case C:
                //If the tile after the Crate is a Stone we don't move. Also our Player cannot move a Crate behind a Crate
                if (mapM.getSingleElement(r + 2, c) == S || mapM.getSingleElement(r + 2, c) == C) {
                    System.out.println("You can't move this!");
                    //If not we move
                } else {
                    //Find the Crate out of List of Crates
                    for (Crate crate : Crates) {
                        if (crate.getRowPos() == (r + 1) && crate.getColPos() == c) {
                            boolean In2TilesMark = mapM.getSingleElement(r + 2, c) == M;
                            //transferring information about marks on the ground
                            if (crate.IsOnMark) {
                                mapM.setSingleElement((r + 2), c, C); //Push Crate 1 TIle in Matrix
                                crate.setRowPos(r + 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark()) {
                                    mapM.setSingleElement(r, c, M);
                                } else {
                                    mapM.setSingleElement(r, c, G);
                                }
                                //Moving the Player
                                Player.setRowPos(r + 1); //Update Player's Row
                                mapM.setSingleElement(r + 1, c, P); //Update PLayer in Matrix

                                Player.setIsOnMark(true); //If Crate was on Mark, Player will be On Mark
                                crate.setIsOnMark(false);
                            } else {
                                mapM.setSingleElement((r + 2), c, C); //Push Crate 1 TIle in Matrix
                                crate.setRowPos(r + 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark()) {
                                    mapM.setSingleElement(r, c, M);
                                } else {
                                    mapM.setSingleElement(r, c, G);
                                }
                                Player.setRowPos(r + 1); //Update Player's Row
                                mapM.setSingleElement(r + 1, c, P); //Update PLayer in Matrix

                                crate.setRowPos(r + 2);
                                mapM.setSingleElement((r + 2), c, C);
                                Player.setIsOnMark(false); //If Crate was on Mark, Player will be NOT On Mark
                            }
                            if (In2TilesMark) {
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

    public void MoveRight() {

        int r;
        int c;
        r = Player.getRowPos();
        c = Player.getColPos();

        //Check what the next tile is
        switch (mapM.getSingleElement(r, c + 1)) {
            //if next Tile is a Mark:
            case M:
                if (Player.IsOnMark) {
                    mapM.setSingleElement(r, c, M);
                } else {
                    mapM.setSingleElement(r, c, G);
                }
                Player.setColPos(c + 1);
                mapM.setSingleElement(r, c + 1, P);
                Player.setIsOnMark(true);
                break;
            //If next Tile is Stone we don't move:
            case S:
                System.out.println("Stone ahead!");
                break;
            //If next Tile is Grass we move
            case G:
                if (Player.IsOnMark) {
                    mapM.setSingleElement(r, c, M);
                } else {
                    mapM.setSingleElement(r, c, G);
                }
                Player.setIsOnMark(false);
                Player.setColPos(c + 1);
                mapM.setSingleElement(r, c + 1, P);
                break;
            //If next tile is a Crate..it gets difficult:
            case C:
                //If the tile after the Crate is a Stone we don't move. Also our Player cannot move a Crate behind a Crate
                if (mapM.getSingleElement(r, c + 2) == S || mapM.getSingleElement(r, c + 2) == C) {
                    System.out.println("You can't move this!");
                    //If not we move
                } else {
                    //Find the Crate out of List of Crates
                    for (Crate crate : Crates) {
                        if (crate.getColPos() == (c + 1) && crate.getRowPos() == r) {
                            boolean In2TilesMark = mapM.getSingleElement(r, c + 2) == M;
                            //transferring information about marks on the ground
                            if (crate.IsOnMark) {
                                mapM.setSingleElement(r, c + 2, C); //Push Crate 1 TIle in Matrix
                                crate.setColPos(c + 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark()) {
                                    mapM.setSingleElement(r, c, M);
                                } else {
                                    mapM.setSingleElement(r, c, G);
                                }
                                //Moving the Player
                                Player.setColPos(c + 1); //Update Player's Row
                                mapM.setSingleElement(r, c + 1, P); //Update PLayer in Matrix

                                Player.setIsOnMark(true); //If Crate was on Mark, Player will be On Mark
                                crate.setIsOnMark(false);
                            } else {
                                mapM.setSingleElement(r, c + 2, C); //Push Crate 1 TIle in Matrix
                                crate.setColPos(c + 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark()) {
                                    mapM.setSingleElement(r, c, M);
                                } else {
                                    mapM.setSingleElement(r, c, G);
                                }
                                Player.setColPos(c + 1); //Update Player's Row
                                mapM.setSingleElement(r, c + 1, P); //Update PLayer in Matrix

                                crate.setColPos(c + 2);
                                mapM.setSingleElement(r, c + 2, C);
                                Player.setIsOnMark(false); //If Crate was on Mark, Player will be NOT On Mark
                            }
                            if (In2TilesMark) {
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

    public void MoveLeft() {

        int r;
        int c;
        r = Player.getRowPos();
        c = Player.getColPos();

        //Check what the next tile is
        switch (mapM.getSingleElement(r, c - 1)) {
            //if next Tile is a Mark:
            case M:
                if (Player.IsOnMark) {
                    mapM.setSingleElement(r, c, M);
                } else {
                    mapM.setSingleElement(r, c, G);
                }
                Player.setColPos(c - 1);
                mapM.setSingleElement(r, c - 1, P);
                Player.setIsOnMark(true);
                break;
            //If next Tile is Stone we don't move:
            case S:
                System.out.println("Stone ahead!");
                break;
            //If next Tile is Grass we move
            case G:
                if (Player.IsOnMark) {
                    mapM.setSingleElement(r, c, M);
                } else {
                    mapM.setSingleElement(r, c, G);
                }
                Player.setIsOnMark(false);
                Player.setColPos(c - 1);
                mapM.setSingleElement(r, c - 1, P);
                break;
            //If next tile is a Crate..it gets difficult:
            case C:
                //If the tile after the Crate is a Stone we don't move. Also our Player cannot move a Crate behind a Crate
                if (mapM.getSingleElement(r, c - 2) == S || mapM.getSingleElement(r, c - 2) == C) {
                    System.out.println("You can't move this!");
                    //If not we move
                } else {
                    //Find the Crate out of List of Crates
                    for (Crate crate : Crates) {
                        if (crate.getColPos() == (c - 1) && crate.getRowPos() == r) {
                            boolean In2TilesMark = mapM.getSingleElement(r, c - 2) == M;
                            //transferring information about marks on the ground
                            if (crate.IsOnMark) {
                                mapM.setSingleElement(r, c - 2, C); //Push Crate 1 TIle in Matrix
                                crate.setColPos(c - 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark()) {
                                    mapM.setSingleElement(r, c, M);
                                } else {
                                    mapM.setSingleElement(r, c, G);
                                }
                                //Moving the Player
                                Player.setColPos(c - 1); //Update Player's Row
                                mapM.setSingleElement(r, c - 1, P); //Update PLayer in Matrix

                                Player.setIsOnMark(true); //If Crate was on Mark, Player will be On Mark
                                crate.setIsOnMark(false);
                            } else {
                                mapM.setSingleElement(r, c - 2, C); //Push Crate 1 TIle in Matrix
                                crate.setColPos(c - 2);     //Update Position of the Crate Object
                                if (Player.getIsOnMark()) {
                                    mapM.setSingleElement(r, c, M);
                                } else {
                                    mapM.setSingleElement(r, c, G);
                                }
                                Player.setColPos(c - 1); //Update Player's Row
                                mapM.setSingleElement(r, c - 1, P); //Update PLayer in Matrix

                                crate.setColPos(c - 2);
                                mapM.setSingleElement(r, c - 2, C);
                                Player.setIsOnMark(false); //If Crate was on Mark, Player will be NOT On Mark
                            }
                            if (In2TilesMark) {
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