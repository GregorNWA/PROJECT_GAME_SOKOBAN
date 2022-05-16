import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;

public class Map {

    private String[][] elements;
    private int rows;
    private int columns;

    public Map(int new_rows, int new_columns) {
        this.elements = new String[new_rows][new_columns];
        this.rows = new_rows;
        this.columns = new_columns;
    }

    static final String S  = "  ▀▀  ";
    static final String P  = "  P1  ";
    static final String G  = "  --  ";
    static final String T  = "  TT  ";
    static final String C  = "  []  ";
    static final String M  = "  ()  ";
    static final String MC = "  [.]  ";

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

    public void MoveUp(Player Player, Map Map, List<Crate> Crates) {

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


    public void MoveDown(Player Player, Map Map, List<Crate> Crates) {

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


    public void MoveRight(Player Player, Map Map, List<Crate> Crates) {

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


    public void MoveLeft(Player Player, Map Map, List<Crate> Crates) {

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