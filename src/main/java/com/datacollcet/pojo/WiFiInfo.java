package com.datacollcet.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WiFiInfo {
    private int rpid;
    private int subid;
    private float Xaxis;
    private float Yaxis;
    private List<Map<String, Integer>> WiFiFeatures = new ArrayList<>();
    private List<List<Float>> magReads = new ArrayList<>();
    private String macAddr;
    private long timeStamp;
    private int sampleingIntervel;

    public WiFiInfo(){}

    public WiFiInfo(int rpid, int subid, float xaxis, float yaxis, List<Map<String, Integer>> wiFiFeatures,
                    String macAddr, long timeStamp, List<List<Float>> magReads, int intervel) {
        this.rpid = rpid;
        this.subid = subid;
        this.Xaxis = xaxis;
        this.Yaxis = yaxis;
        WiFiFeatures = wiFiFeatures;
        this.macAddr = macAddr;
        this.timeStamp = timeStamp;
        this.magReads = magReads;
        this.sampleingIntervel = intervel;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getRpid() {
        return rpid;
    }

    public void setRpid(int rpid) {
        this.rpid = rpid;
    }

    public int getSubid() {
        return subid;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }

    public float getXaxis() {
        return Xaxis;
    }

    public void setXaxis(float xaxis) {
        Xaxis = xaxis;
    }

    public float getYaxis() {
        return Yaxis;
    }

    public void setYaxis(float yaxis) {
        Yaxis = yaxis;
    }

    public List<Map<String, Integer>> getWiFiFeatures() {
        return WiFiFeatures;
    }

    public void setWiFiFeatures(List<Map<String, Integer>> wiFiFeatures) {
        WiFiFeatures = wiFiFeatures;
    }

    public List<List<Float>> getMagReads() {
        return magReads;
    }

    public void setMagReads(List<List<Float>> magReads) {
        this.magReads = magReads;
    }

    public int getSampleingIntervel() {
        return sampleingIntervel;
    }

    public void setSampleingIntervel(int sampleingIntervel) {
        this.sampleingIntervel = sampleingIntervel;
    }

    @Override
    public String toString() {
        return "WiFiInfo{" +
                "rpid=" + rpid +
                ", subid=" + subid +
                ", Xaxis=" + Xaxis +
                ", Yaxis=" + Yaxis +
                ", WiFiFeatures=" + WiFiFeatures +
                ", magReads=" + magReads +
                ", macAddr='" + macAddr + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}