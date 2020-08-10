package com.datacollcet.pojo;

public class Mag {
    private int id;
    private float reading;

    public Mag(int id, float reading) {
        this.id = id;
        this.reading = reading;
    }
    public Mag(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getReading() {
        return reading;
    }

    public void setReading(float reading) {
        this.reading = reading;
    }
}
