package com.company.hotel;

import com.company.area.Data;
import com.company.area.Dimensions;

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

    //because variables are private
    public Position getPosition() {
        return this.position;
    }
    public Dimensions getDimensions() {return this.dimensions; }

    public String getType() {
        return type;
    }

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
