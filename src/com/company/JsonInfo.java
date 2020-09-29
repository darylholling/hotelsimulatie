package com.company;

class JsonInfo {

    private final String type;
    private final int x;
    private final int y;
    private final int areaWidth;
    private final int areaHeight;
    private final Data data;
    private final Integer time;

    public JsonInfo(String type, Integer time, int x, int y, int areaWidth, int areaHeight, Data data) {
        this.type = type;
        this.data = data;
        this.time = time;
        this.x = x;
        this.y = y;
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;
    }

    public String getType() { return type;
    }
    public Integer getTime(){return time;}

    public Data getData() {
        return data;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAreaWidth() {
        return areaWidth;
    }

    public int getAreaHeight() {
        return areaHeight;
    }
}

