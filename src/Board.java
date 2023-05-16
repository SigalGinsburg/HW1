import java.util.Arrays;

public class Board {
    private final int n;
    private final int m;
    private Tile [][] tiles;
    private Integer[] placeOfZero;

    public Board (String instruction){
        String [] rows= instruction.split("\\|");
        this.m= rows.length;
        String [] row= rows[0].split(" ");
        this.n=row.length;

        tiles = new Tile[m][n];
        for (int i = 0; i < m; i++) {
            row = rows[i].split(" ");
            for (int j = 0; j < n; j++) {
                tiles[i][j] = new Tile(row[j]);
                if (tiles[i][j].getValue() == 0){
                    this.placeOfZero = new Integer[2];
                    this.placeOfZero[0] = i;
                    this.placeOfZero[1] = j;
                }
            }
        }
    }
        public Board(int m, int n){
            this.m = m;
            this.n = n;
            this.tiles = new Tile[m][n];
            int k = m * n;
            int l = 0;
            while (l<=k){
            for (int i=0; i<m; i++){
                for (int j=0; j<n; j++){
                    tiles[i][j] = new Tile((m * n)-l);
                    l++;
                }
            }
        }

    }
    public int getM(){
        return this.m;
    }
    public int getN(){
        return this.n;
    }
    public Integer[] getPlaceOfZero(){
        return this.placeOfZero;
    }

    public Tile [][] getTiles(){
        return this.tiles;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
