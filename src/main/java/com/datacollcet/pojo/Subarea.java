package com.datacollcet.pojo;

public class Subarea {
    private int sbid;
    private double leftside;
    private double rightside;
    private double upside;
    private double bottomside;

    public  Subarea(){}
    public Subarea(int sbid, double leftside, double rightside, double upside, double bottomside) {
        this.sbid = sbid;
        this.leftside = leftside;
        this.rightside = rightside;
        this.upside = upside;
        this.bottomside = bottomside;
    }

    public int getSbid() {
        return sbid;
    }

    public void setSbid(int sbid) {
        this.sbid = sbid;
    }

    public double getLeftside() {
        return leftside;
    }

    public void setLeftside(double leftside) {
        this.leftside = leftside;
    }

    public double getRightside() {
        return rightside;
    }

    public void setRightside(double rightside) {
        this.rightside = rightside;
    }

    public double getUpside() {
        return upside;
    }

    public void setUpside(double upside) {
        this.upside = upside;
    }

    public double getBottomside() {
        return bottomside;
    }

    public void setBottomside(double bottomside) {
        this.bottomside = bottomside;
    }
}
