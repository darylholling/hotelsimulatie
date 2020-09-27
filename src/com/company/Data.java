package com.company;

public class Data {
    Integer stars;
    Integer max;
//    Integer duration;
//    Integer guest;
//    Integer time;
//
    public Data(Integer stars) {
        this.stars = stars;
    }

    public Integer getStars() {
        return stars;
    }
    public Integer getMax() { return max; }
//    public Integer getDuration() {return duration; }
//    public Integer getGuest() { return guest; }
//    public Integer getTime() { return time; }

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
