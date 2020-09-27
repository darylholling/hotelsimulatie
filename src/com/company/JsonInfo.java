package com.company;

class JsonInfo {

    private final String type;
    private final Position position;
    private final Dimensions dimensions;
    private final Data data;
    private final Integer time;

    public JsonInfo(String type, Integer time, Position position, Dimensions dimensions, Data data) {
        this.type = type;
        this.position = position;
        this.dimensions = dimensions;
        this.data = data;
        this.time = time;
    }

    //because variables are private
    public Position getPosition() {
        return this.position;
    }
    public Dimensions getDimensions() {return this.dimensions; }

    public String getType() { return type;
    }
    public Integer getTime(){return time;}

    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Layout{" +
                "type='" + type + '\'' +
                ", position=" + position +
                ", dimensions=" + dimensions +
                ", data=" + data +
                '}';
    }
}

