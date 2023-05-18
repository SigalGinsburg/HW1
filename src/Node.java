public class Node {
    private final State state;
    private final Node parent;
    private final Action action;

    public Node(State state){
        this.state = state;
        this.parent = null;
        this.action = null;
    }
    private Node(State state,Node previousVertex, Action action ){
        this.state = state;
        this.parent = previousVertex;
        this.action = action;
    }
    public State getState(){
        return this.state;
    }

    public Action getAction(){
        return this.action;
    }

    public Node getParent(){
        return this.parent;
    }

    public Node[] expand(){
       Action[] actions = this.state.actions();
       Node[] children = new Node[actions.length];
       for(int i = 0; i < actions.length; i++){
           State temp = this.state.result(actions[i]);
           children[i] = new Node(temp, this, actions[i]);
       }
       return children;
    }

    public int heuristicValue(){
        return this.state.heuristicValue();
    }

}
