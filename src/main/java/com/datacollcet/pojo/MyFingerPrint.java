package com.datacollcet.pojo;


public class MyFingerPrint {
    private int id;
    private String rssi;
    private double xaxis;
    private double yaxis;
    private double rank;
    private int subarea;


    public MyFingerPrint() {}

    public MyFingerPrint(int id, String rssi, double xasix, double yasix, double rank, int subarea) {
        this.id = id;
        this.rssi = rssi;
        this.xaxis = xasix;
        this.yaxis = yasix;
        this.rank = rank;
        this.subarea = subarea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getXaxis() {
        return xaxis;
    }

    public void setXaxis(float xaxis) {
        this.xaxis = xaxis;
    }

    public double getYaxis() {
        return yaxis;
    }

    public void setYaxis(float yaxis) {
        this.yaxis = yaxis;
    }


    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public double getRank() {
        return rank;
    }
    public void setRank(double rank) {
        this.rank = rank;
    }

    public int getSubarea() {
        return subarea;
    }

    public void setSubarea(int subarea) {
        this.subarea = subarea;
    }
}
