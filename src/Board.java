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
        this.n=row.length; // The length of a single row is the number of columns.

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

    /**
     * This method is a second constructor used to create a target board
     * @param m represents the number of rows in the board
     * @param n represents the number of columns in the board
     */
        public Board(int m, int n){
            this.m = m;
            this.n = n;
            this.tiles = new Tile[m][n];
            int l = 1; // This variable is used to indicate the value of each tile placed in the board.
            for (int i=0; i<m; i++){
                for (int j=0; j<n; j++){
                    tiles[i][j] = new Tile(l);
                    l++;
                }
            }
            tiles[m-1][n-1] = new Tile(0); // we place the 0 in the last place in the board

            this.placeOfZero = new Integer[2];
            this.placeOfZero[0] = m-1;
            this.placeOfZero[1] = n-1;

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
        int rowForTile;
        int colForTile;

        if (!(this.placeOfZero[0]==m-1)) {

            rowForTile=this.placeOfZero[0]+1;
            colForTile=this.placeOfZero[1];
            oneAction= new Action(this.tiles[rowForTile][colForTile],Direction.UP);
            actions[countIndex] = oneAction;
            countIndex++;
        }

        if (!(this.placeOfZero[0]==0)) {
            rowForTile=this.placeOfZero[0]-1;
            colForTile=this.placeOfZero[1];
            oneAction= new Action(this.tiles[rowForTile][colForTile],Direction.DOWN);
            actions[countIndex] = oneAction;
            countIndex++;
        }

        if (!(this.placeOfZero[1]==0)) {

            rowForTile=this.placeOfZero[0];
            colForTile=this.placeOfZero[1]-1;
            oneAction= new Action(this.tiles[rowForTile][colForTile],Direction.RIGHT);
            actions[countIndex] = oneAction;
            countIndex++;


        }

        if (!(this.placeOfZero[1]==n-1)) {
            rowForTile=this.placeOfZero[0];
            colForTile=this.placeOfZero[1]+1;
            oneAction= new Action(this.tiles[rowForTile][colForTile],Direction.LEFT);
            actions[countIndex] = oneAction;

        }

        return actions;
    }

    /**
     * This method is used to move a single tile in the board.
     * The action is done by switching the tile given in "action" with the "0" tile
     * After each action we update the "placeOfZero" array.
     * @param action we receive one of four actions - UP, DOWN, LEFT or RIGHT
     */
    public void doAction(Action action) {
        int row = this.placeOfZero[0];
        int column = this.placeOfZero[1];
        if (action.getDirection() == Direction.UP) {
            this.tiles[row+1][column] = new Tile(0);
            this.placeOfZero[0] = row +1;
            this.placeOfZero[1] = column;
            this.tiles[row][column] = action.getTile();
        }
        if (action.getDirection() == Direction.DOWN) {
            this.tiles[row-1][column] = new Tile(0);
            this.placeOfZero[0] = row - 1;
            this.placeOfZero[1] = column;
            this.tiles[row][column] = action.getTile();
        }
        if (action.getDirection() == Direction.RIGHT) {
            this.tiles[row][column-1] = new Tile(0);
            this.placeOfZero[0] = row ;
            this.placeOfZero[1] = column - 1;
            this.tiles[row][column] = action.getTile();
        }
        if (action.getDirection() == Direction.LEFT) {
            this.tiles[row][column+1] = new Tile(0);
            this.placeOfZero[0] = row ;
            this.placeOfZero[1] = column + 1;
            this.tiles[row][column] = action.getTile();
        }
    }

    /**
     * This method checks the difference between two parameters
     * We use it to aid the "distanceFromTarget" function
     * @param x1 our current row/ column
     * @param x2 our target row/ column
     * @return the distance between the two
     */
    private int difference(int x1, int x2){
        if(x1>x2)
            return x1 - x2;
        return x2-x1;
    }

    /**
     * This method is used to check a single tile's distance from its target location.
     * @param tile a tile we want to check
     * @param i the row number it's located at.
     * @param j the column number it's located at.
     * @return the distance
     */
    private int distanceFromTarget(Tile tile, int i, int j) {
        int v = tile.getValue() - 1;
        if (v == -1){
            return 0;
        }
        int expected_j = v % n;
        int expected_i = v / n;
        return difference(j, expected_j) + difference(i, expected_i);
    }

    /**
     * This method is used to determine the heuristic value which helps the algorithm
     * solve the game more efficiently
     * The heuristic value we created is determined by the amount of tiles out of order,
     * and the sum of their distances from each target location
     * @return the heuristic value for each board
     */
    public int heuristicValue(){
        int res = 0;
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                res += distanceFromTarget(tiles[i][j], i, j);
            }
        }
        return res;
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    /**
     * This method is used to create a copy of a given board.
     * @return a copy of a given board
     */
    public Board copy(){
      Board boardCopy = new Board(m,n); // First we place a target board in the copy for convenience
        // now we start changing each tile to fit the one in the board we would like to copy
      for (int i=0; i<m; i++){
          for (int j=0; j<n; j++){
              boardCopy.tiles[i][j] = this.tiles[i][j];
          }
      }
      boardCopy.placeOfZero[0] = this.placeOfZero[0]; // we update the "placeOfZero" array in the copy
      boardCopy.placeOfZero[1] = this.placeOfZero[1];
      return boardCopy;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
