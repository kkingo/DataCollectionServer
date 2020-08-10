package com.datacollcet.pojo;

public class Readings {
    private double xReadings, yReadings, zReadings;
    private int orders;


    public Readings() {}

    public Readings(double xReadings, double yReadings, double zReadings, int orders) {
        this.xReadings = xReadings;
        this.yReadings = yReadings;
        this.zReadings = zReadings;
        this.orders = orders;
    }

    public double getxReadings() {
        return xReadings;
    }

    public void setxReadings(double xReadings) {
        this.xReadings = xReadings;
    }

    public double getyReadings() {
        return yReadings;
    }

    public void setyReadings(double yReadings) {
        this.yReadings = yReadings;
    }

    public double getzReadings() {
        return zReadings;
    }

    public void setzReadings(double zReadings) {
        this.zReadings = zReadings;
    }

    public String toString(){
        return xReadings+":"+yReadings+":"+zReadings;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

}
