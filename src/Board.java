import java.util.Arrays;

public class Board {
    private final int n;
    private final int m;
    private Tile [][] board;

    public Board (String instruction){
        String [] rows= instruction.split("\\|");
        this.m= rows.length;
        String [] row= rows[0].split(" ");
        this.n=row.length;

        board= new Tile[m][n];
        for (int i=0; i<m; i++){
            row=rows[i].split(" ");
            for (int j=0; j<n; j++){
                board[i][j]= new Tile(row[j]);
            }
        }

    }

    public Tile [][] getBoard (){
        return this.board;
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
