package com.datacollcet.pojo;

public class Relative {
    private int id;
    private double rank;

    public Relative(){}
    public Relative(int id, double rank) {
        this.id = id;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }
}
