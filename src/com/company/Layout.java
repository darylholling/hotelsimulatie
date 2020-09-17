public class Layout {

    private final String type;
    private final Position position;
    private final Dimensions dimensions;
    private final Data data;

    public Layout(String type, Position position, Dimensions dimensions, Data data) {
        this.type = type;
        this.position = position;
        this.dimensions = dimensions;
        this.data = data;
    }


    @Override
    public String toString() {
        String a = "Type: "+ type + ", Position x/y: " +position.x +" "+ position.y+", Dimensions: "+dimensions.width+" x "+dimensions.height+", Rating (in stars): "+data.stars;
            return a;

    }

    //because variables are private
    public Position getPosition() {
        return this.position;
    }
    public Dimensions getDimensions() {return this.dimensions; }
}
