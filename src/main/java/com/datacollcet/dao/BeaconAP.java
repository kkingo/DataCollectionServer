package com.datacollcet.dao;

import java.util.LinkedHashMap;
import java.util.Map;

public class BeaconAP {
    private Map<String, Integer> rssi = new LinkedHashMap<>();
    private String timeStamp;
    private Map<String, Integer> APfreq = new LinkedHashMap<>();

    public Map<String, Integer> getRssi() {
        return rssi;
    }

    public void setRssi(Map<String, Integer> rssi) {
        this.rssi = rssi;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Map<String, Integer> getAPfreq() {
        return APfreq;
    }

    public void setAPfreq(Map<String, Integer> APfreq) {
        this.APfreq = APfreq;
    }
}
