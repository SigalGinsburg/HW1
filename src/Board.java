import javax.print.DocFlavor;
import java.util.Arrays;

public class Board {
    // n represent the number of columns in the board
    private final int n;
    // m represent the number of rows in the board
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

    public Action[] actions(){
        int countDirections=4;

        if (this.placeOfZero[0]==0)
            countDirections--;
        if (this.placeOfZero[0]==m-1)
            countDirections--;
        if (this.placeOfZero[1]==0)
            countDirections--;
        if (this.placeOfZero[1]==n-1)
            countDirections--;

        Action[] actions=new Action[countDirections];
        int countIndex=0;
        Action oneAction;
        int rowForTale;
        int colForTale;

        if (!(this.placeOfZero[0]==0)) {
            rowForTale=this.placeOfZero[0]-1;
            colForTale=this.placeOfZero[1];
            oneAction= new Action(this.tiles[rowForTale][colForTale],Direction.UP);
            actions[countIndex] = oneAction;
            countIndex++;
        }

        if (!(this.placeOfZero[0]==m-1)) {

            rowForTale=this.placeOfZero[0]+1;
            colForTale=this.placeOfZero[1];
            oneAction= new Action(this.tiles[rowForTale][colForTale],Direction.DOWN);
            actions[countIndex] = oneAction;
            countIndex++;
        }

        if (!(this.placeOfZero[1]==0)) {

            rowForTale=this.placeOfZero[0];
            colForTale=this.placeOfZero[1]-1;
            oneAction= new Action(this.tiles[rowForTale][colForTale],Direction.RIGHT);
            actions[countIndex] = oneAction;
            countIndex++;


        }

        if (!(this.placeOfZero[1]==n-1)) {
            rowForTale=this.placeOfZero[0];
            colForTale=this.placeOfZero[1]+1;
            oneAction= new Action(this.tiles[rowForTale][colForTale],Direction.LEFT);
            actions[countIndex] = oneAction;

        }

        return actions;
    }


    public void doAction(Action action) {
        int row = this.getPlaceOfZero()[0];
        int column = this.getPlaceOfZero()[1];
        if (action.getDirection() == Direction.UP) {
            this.tiles[row+1][column] = new Tile(0);
            this.tiles[row][column] = action.getTile();
        }
        if (action.getDirection() == Direction.DOWN) {
            this.tiles[row-1][column] = new Tile(0);
            this.tiles[row][column] = action.getTile();
        }
        if (action.getDirection() == Direction.RIGHT) {
            this.tiles[row][column-1] = new Tile(0);
            this.tiles[row][column] = action.getTile();
        }
        if (action.getDirection() == Direction.LEFT) {
            this.tiles[row][column+1] = new Tile(0);
            this.tiles[row][column] = action.getTile();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    public Board copy(){
      Board boardCopy = new Board(m,n);
      for (int i=0; i<m; i++){
          for (int j=0; j<n; j++){
              boardCopy.tiles[i][j] = this.tiles[i][j];
          }
      }
      return boardCopy;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
