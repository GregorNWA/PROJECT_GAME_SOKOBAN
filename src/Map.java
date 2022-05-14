public class Map {

    private String[][] elements;
    private int rows;
    private int columns;

    public Map(int new_rows, int new_columns){
    this.elements = new String[new_rows][new_columns];
    this.rows = new_rows;
    this.columns = new_columns;
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

    }




    public String MoveUp(){
    }
    */

    public static void main(String[] args) {
        String S = "Stone";
        String P = "Player";
        String G = "Grass";

        String[][] MAP = {
                {S, S, S},
                {S, P, S},
                {G, G, G},
                {S, S, S}};

        Map MAP1 = new Map(4,3);
        MAP1.setElements(MAP);

        System.out.println(MAP1);

        //Change Player Position



    }
}
