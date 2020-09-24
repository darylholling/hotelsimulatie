package com.company;

public class Data {
    Integer stars;
    Integer max;

    public Data(Integer stars) {
        this.stars = stars;
    }

    public Integer getStars() {
        return stars;
    }
    public Integer getMax() { return max; }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Data{" +
                "stars=" + stars +
                "max capacity= "+ max +
                '}';
    }
}
