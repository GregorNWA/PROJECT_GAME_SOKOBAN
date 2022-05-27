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

    //automatic matrix size recognition
    static int lvl1rows = Level_1.length;
    static int lvl1cols = Level_1.length;

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

    static int lvl2rows = Level_2.length;
    static int lvl2cols = Level_2.length;

    Player Player;
    List<Crate> Crates;
    int currLvl = 1;
    private Map mapM;
    private String[][] elements;

    //Map's arguments
    private int rows;
    private int columns;

    public Map(int new_rows, int new_columns) {
        this.elements = new String[new_rows][new_columns];
        this.rows = new_rows;
        this.columns = new_columns;
    }

    //Initializes Map based on Matrix
    public void initializeMap(Map map) {
        map.setElements(Level_1);
        this.mapM = map;
    }

    //Initializes the player based on the matrix
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

    //Initialize the crates based on the matrix
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


    public Map getMapM() {
        return mapM;
    }

    //Getters
    public int getColumns() {
        return columns;
    }

    //Setters
    public int getRows() {
        return rows;
    }

    public String getElements(int rows, int columns) {
        return elements[rows][columns];
    }

    public String getSingleElement(int row, int column) {
        if (row >= 0 && column >= 0 && row < rows && column < columns)
            return elements[row][column];
        return null;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setElements(String[][] elements) {
        this.elements = elements;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setSingleElement(int row, int column, String tile) {
        if (row >= 0 && column >= 0 && row < rows && column < columns) {
            elements[row][column] = tile;
        }
    }

    //Pretty printer for the map
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

    //Method to verify if the winning conditions are meet
    public int checkLevel() {
        //Count the crates and see how many are marked
        int count = 0;
        for (Crate crate : Crates) {
            if (crate.IsOnMark) {
                count++;
            }
        }

        //If they are all marked then you have win the level 1
        if ((count >= Crates.size())) {
            //Condition entered only once, after clearing the level 1
            if (currLvl == 1) {
                //Load the second level and initialize everything
                mapM.setElements(Level_2);
                mapM.initializePlayer(mapM);
                Crates.clear();
                mapM.initializeCrates(getMapM());
                count = 0;
                currLvl = 2; //Make sure we don't enter this condition anymore
                return currLvl;
            }

            //Count the crates and see how many are marked
            for (Crate crate : Crates) {
                if (crate.IsOnMark) {
                    count++;
                }
            }
            //Game win/finished condition
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
        switch (mapM.getSingleElement(r - 1, c)) { //r-1 means the row above
            //if next Tile is a Mark:
            case M:
                if (Player.IsOnMark) {
                    mapM.setSingleElement(r, c, M); //Replace the player by a mark if he is on one
                } else {
                    mapM.setSingleElement(r, c, G); //Or by grass (blank)
                }
                Player.setRowPos(r - 1); //Update the player's row
                mapM.setSingleElement(r - 1, c, P);//Update the player in the matrix
                Player.setIsOnMark(true); //As the next tile is a mark the player will be on a mark
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
            //If next tile is a Crate.. it gets difficult:
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
                            //Transferring information about marks on the ground
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
                                mapM.setSingleElement((r - 2), c, C); //Push Crate 1 Tile in Matrix
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

    //The three next methods have exactly the same design but not the same direction

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