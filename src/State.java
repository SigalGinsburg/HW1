public class State {
    private final Board board;
    private final Board targetBoard;

    /** constructor that creates the initial board. */
    public State(Board board){
        this.board=board;
        int m=board.getM();
        int n=board.getN();
        this.targetBoard = new Board(m, n);
    }

    /** constructor that creates all boards except the initial board. It copies the target board because
     *it's the same target board for all state's objects and there is no need to create it from the beginning each time
     */
    private State(Board board, Board targetBoard){
        this.board = board;
        this.targetBoard = targetBoard;
    }
    /** this function checks if the game is done by comparing the current board to the target board */
    public boolean isGoal(){
        return this.board.equals(this.targetBoard);

    }

    /**
     * This method receives an action and executes it.
     * @param action a tile and a direction in which we want to move it.
     * @return a new state where the change is made.
     */
    public State result(Action action){
        State newState = new State(board.copy(), targetBoard);
        newState.board.doAction(action);
        return newState;
    }

    public Action[] actions(){
        return this.board.actions();
    }

    public int getHeuristicValue(){
        return this.board.heuristicValue();
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
