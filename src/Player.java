public class Player {
    int rowPos;
    int colPos;

    public Player(int new_rowPos,int new_colPos){
        this.rowPos=new_rowPos;
        this.colPos=new_colPos;

    };

    public void setColPos(int colPos) {
        this.colPos = colPos;
    }

    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public int getColPos() {
        return colPos;
    }

    public int getRowPos() {
        return rowPos;
    }
}
