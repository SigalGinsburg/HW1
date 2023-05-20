public class Tile {
    private final int value;

    public Tile (int value){
        this.value = value;
    }

    /**
     * This method is a second constructor.
     * instead of "_" we place a 0 for our convenience in different methods.
     * @param stringValue is a string containing a number or "_".
     */
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