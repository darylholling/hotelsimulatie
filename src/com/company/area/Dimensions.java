package com.company.area;

public class Dimensions {
    Integer width;
    Integer height;
    String type; // Dit moet later een Object worden!
    Integer rating;

    public Dimensions(Integer width, Integer height, String type, Integer rating) {
        this.width = width;
        this.height = height;
        this.type = type;
        this.rating = rating;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public String getType() {
        return type;
    }

    public Integer getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Dimensions{" +
                "width=" + width +
                ", height=" + height +
                ", type='" + type + '\'' +
                ", rating=" + rating +
                '}';
    }
}
