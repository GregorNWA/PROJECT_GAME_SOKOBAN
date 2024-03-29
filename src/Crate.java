public class Crate {
    //Crate's arguments
    int rowPos;
    int colPos;
    boolean IsOnMark;

    public Crate(int new_rowPos, int new_colPos, boolean IsOnMark) {
        this.rowPos = new_rowPos;
        this.colPos = new_colPos;
        this.IsOnMark = IsOnMark;
    }

//Getters
    public int getColPos() {
        return colPos;
    }
    public int getRowPos() {
        return rowPos;
    }
    public boolean getIsOnMark() {
        return IsOnMark;
    }

    //Setters
    public void setColPos(int colPos) {
        this.colPos = colPos;
    }
    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }
    public void setIsOnMark(boolean IsOnMark) {
        this.IsOnMark = IsOnMark;
    }
}