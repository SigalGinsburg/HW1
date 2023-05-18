public class Node {
    private final State state;
    private final Node previousVertex;
    private final Action action;

    public Node(State state){
        this.state = state;
        this.previousVertex = null;
        this.action = null;
    }

}
