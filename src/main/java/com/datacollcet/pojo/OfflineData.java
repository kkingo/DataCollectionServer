package com.datacollcet.pojo;

import lombok.Data;

import java.sql.SQLOutput;

//@Data
public class OfflineData {
    private int RPID;
    private int SBID;
    private  String SSID;
    private String MAC;
    private double Rssi;

    public OfflineData(){
//        System.out.println("king");
    }

    public OfflineData(int RPID, int SBID, String SSID, String MAC, double Rssi) {
        this.RPID = RPID;
        this.SBID = SBID;
        this.SSID = SSID;
        this.MAC = MAC;
        this.Rssi = Rssi;
    }
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder(RPID);
        stringBuilder.append(SBID);
        stringBuilder.append(SSID);
        stringBuilder.append(MAC);
        stringBuilder.append(Rssi);
        return stringBuilder.toString();
    }
//
//    public int getRPID() {
//        return RPID;
//    }
//
//    public void setRPID(int RPID) {
//        System.out.println(RPID);
//        this.RPID = RPID;
//    }
//
//    public int getSBID() {
//        return SBID;
//    }
//
//    public void setSBID(int SBID) {
//        System.out.println(SBID);
//        this.SBID = SBID;
//    }
//
//    public String getSSID() {
//        return SSID;
//    }
//
//    public void setSSID(String SSID) {
//        this.SSID = SSID;
//    }
//
//    public String getMAC() {
//        return MAC;
//    }
//
//    public void setMAC(String MAC) {
//        System.out.println(MAC);
//        this.MAC = MAC;
//    }
//
//    public double getRssi() {
//        return Rssi;
//    }
//
//    public void setRssi(double rssi) {
//        System.out.println(rssi);
//        Rssi = rssi;
//    }


    public int getRPID() {
        return RPID;
    }

    public void setRPID(int RPID) {
        this.RPID = RPID;
    }

    public int getSBID() {
        return SBID;
    }

    public void setSBID(int SBID) {
        this.SBID = SBID;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public double getRssi() {
        return Rssi;
    }

    public void setRssi(double rssi) {
        Rssi = rssi;
    }
}
