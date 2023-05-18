import java.security.cert.TrustAnchor;

public class State {
    private final Board board;
    private final Board targetBoard;

    private State(Board board){
        this.board=board;
        int m=board.getM();
        int n=board.getN();
        this.targetBoard = new Board(m, n);
    }
    private State(Board board, Board targetBoard){
        this.board = board;
        this.targetBoard = targetBoard;
    }
    public boolean isGoal(){
        return this.board.equals(this.targetBoard);

    }
    public State result(Action action){
        State newState = new State(board.copy(), targetBoard);
        newState.board.doAction(action);
        return newState;
    }


    public Action[] actions(){
        Action[] actions=this.board.actions();
        return actions;
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
