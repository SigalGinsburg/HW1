public class Action {
    private String action;
    private Tile tile;
    private Direction direction;

    public Action(Tile tile, Direction direction){
        this.tile = tile;
        this.direction = direction;
    }
    @Override
    public String toString(){
        return "Move " + this.tile.getValue() + " " + this.direction;
    }
}
