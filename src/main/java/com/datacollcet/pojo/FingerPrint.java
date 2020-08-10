package com.datacollcet.pojo;


public class FingerPrint {
    private int id;
    private String rssi;
    private double xaxis;
    private double yaxis;
    private double rank;
    private int subarea;


    public FingerPrint() {}

    public FingerPrint(int id, String rssi, double xasix, double yasix) {
        this.id = id;
        this.rssi = rssi;
        this.xaxis = xasix;
        this.yaxis = yasix;
        this.rank = -1;
        this.subarea = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public double getXaxis() {
        return xaxis;
    }

    public void setXaxis(double xaxis) {
        this.xaxis = xaxis;
    }

    public double getYaxis() {
        return yaxis;
    }

    public void setYaxis(double yaxis) {
        this.yaxis = yaxis;
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

    @Override
    public String toString() {
        return "FingerPrint{" +
                "id=" + id +
                ", rssi='" + rssi + '\'' +
                ", xaxis=" + xaxis +
                ", yaxis=" + yaxis +
                ", rank=" + rank +
                ", subarea=" + subarea +
                '}';
    }
}
