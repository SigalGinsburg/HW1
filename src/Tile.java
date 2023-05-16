public class Tile {
    private final int value;

    public Tile (int value){
        this.value = value;
    }
    public Tile(String stringValue){
        if(stringValue.equals("_")){
            this.value = 0;
        }
        else{
            this.value = Integer.parseInt(stringValue);
        }

    }

    public int getValue() {
        return this.value;
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}