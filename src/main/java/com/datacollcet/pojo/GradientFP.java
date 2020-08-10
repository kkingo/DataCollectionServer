package com.datacollcet.pojo;

public class GradientFP {
    private int xstart;
    private int ystart;
    private int xend;
    private int yend;
    private String gradient;

    public GradientFP(){}

    public GradientFP(int xstart, int ystart, int xend, int yend, String gradient) {
        this.xstart = xstart;
        this.ystart = ystart;
        this.xend = xend;
        this.yend = yend;
        this.gradient = gradient;
    }

    public int getXstart() {
        return xstart;
    }

    public void setXstart(int xstart) {
        this.xstart = xstart;
    }

    public int getYstart() {
        return ystart;
    }

    public void setYstart(int ystart) {
        this.ystart = ystart;
    }

    public int getXend() {
        return xend;
    }

    public void setXend(int xend) {
        this.xend = xend;
    }

    public int getYend() {
        return yend;
    }

    public void setYend(int yend) {
        this.yend = yend;
    }

    public String getGradient() {
        return gradient;
    }

    public void setGradient(String gradient) {
        this.gradient = gradient;
    }
}
