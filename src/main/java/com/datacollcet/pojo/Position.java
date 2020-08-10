package com.datacollcet.pojo;

/**
 * Created by King on 2018/3/20.
 */

public class Position {
    private int id;
    private double xaxis;
    private double yaxis;
    private String mark;
    private String mac;
    private int direction;


    public Position(int rpid, double xaxis, double yaxis, String mac, int direction) {
        this.id = rpid;
        this.xaxis = xaxis;
        this.yaxis = yaxis;
        this.mac = mac;
        this.direction = direction;
    }

    public Position() {

    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
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

    public void setXaxis(double xaxis) {
        this.xaxis = xaxis;
    }

    public double getYaxis() {
        return yaxis;
    }

    public void setYaxis(double yaxis) {
        this.yaxis = yaxis;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", xaxis=" + xaxis +
                ", yaxis=" + yaxis +
                ", mark=" + mark +
                ", mac='" + mac + '\'' +
                ", direction=" + direction +
                '}';
    }
}

