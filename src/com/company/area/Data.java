package com.company.area;

public class Data {
    Integer stars;

    public Data(Integer stars) {
        this.stars = stars;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Data{" +
                "stars=" + stars +
                '}';
    }
}
