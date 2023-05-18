import java.security.cert.TrustAnchor;

public class State {
    private Board board;
    private Board targetBoard;

    private State(Board board){
        this.board=board;
        int m=board.getM();
        int n=board.getN();
        this.targetBoard = new Board(m, n);
    }
    public boolean isGoal(){
        return this.board.equals(this.targetBoard);

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
