package com.datacollcet.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrajInfo {
    private int trajID;
    private float startX;
    private float startY;
    private String WiFiFeatures;
    private String orientReads;
    private String accReadings;
    private String gyroReadings;
    private String macAddr;
    private String timeStamp;
    private int sampleingIntervel;

    public TrajInfo(){}

    public int getTrajID() {
        return trajID;
    }

    public void setTrajID(int trajID) {
        this.trajID = trajID;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public String getWiFiFeatures() {
        return WiFiFeatures;
    }

    public void setWiFiFeatures(String wiFiFeatures) {
        WiFiFeatures = wiFiFeatures;
    }

    public String getOrientReads() {
        return orientReads;
    }

    public void setOrientReads(String orientReads) {
        this.orientReads = orientReads;
    }

    public String getAccReadings() {
        return accReadings;
    }

    public void setAccReadings(String accReadings) {
        this.accReadings = accReadings;
    }

    public String getGyroReadings() {
        return gyroReadings;
    }

    public void setGyroReadings(String gyroReadings) {
        this.gyroReadings = gyroReadings;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getSampleingIntervel() {
        return sampleingIntervel;
    }

    public void setSampleingIntervel(int sampleingIntervel) {
        this.sampleingIntervel = sampleingIntervel;
    }

    @Override
    public String toString() {
        return "TrajInfo{" +
                "trajID=" + trajID +
                ", startX=" + startX +
                ", startY=" + startY +
                ", WiFiFeatures=" + WiFiFeatures +
                ", magReads=" + orientReads +
                ", accReadings=" + accReadings +
                ", gyroReadings=" + gyroReadings +
                ", macAddr='" + macAddr + '\'' +
                ", timeStamp=" + timeStamp +
                ", sampleingIntervel=" + sampleingIntervel +
                '}';
    }
}