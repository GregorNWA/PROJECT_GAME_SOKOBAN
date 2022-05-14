public class Map {
    public static void main(String[] args) {

        String S = "Stone";
        String P = "Player";
        String G = "Grass";


        String[][] MAP = {
                {S,S,S},
                {S,P,S},
                {G,G,G},
                {S,S,S}};

        for (String[] line : MAP) {
            for (String s : line) {
                System.out.print(s+" ");
            }
            System.out.println("");
        }
    }
}
