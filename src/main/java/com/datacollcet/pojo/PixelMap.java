package com.datacollcet.pojo;

public class PixelMap {
    private int xaxis;
    private int yaxis;
    private int pixel;

    public PixelMap(int xaxis, int yaxis, int pixel) {
        this.xaxis = xaxis;
        this.yaxis = yaxis;
        this.pixel = pixel;
    }

    public PixelMap(){}
    public int getXaxis() {
        return xaxis;
    }

    public void setXaxis(int xaxis) {
        this.xaxis = xaxis;
    }

    public int getYaxis() {
        return yaxis;
    }

    public void setYaxis(int yaxis) {
        this.yaxis = yaxis;
    }

    public int getPixel() {
        return pixel;
    }

    public void setPixel(int pixel) {
        this.pixel = pixel;
    }

    @Override
    public String toString() {
        return "PixelMap{" +
                "xaxis=" + xaxis +
                ", yaxis=" + yaxis +
                ", pixel=" + pixel +
                '}';
    }
}
