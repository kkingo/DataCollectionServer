package com.datacollcet.pojo;

/**
 * Created by King on 2018/8/26.
 */

public class RecordNumber {
    private int order;
    private int extractedNumber;

    public RecordNumber(){}
    public RecordNumber(int order, int extractedNumber) {
        this.order = order;
        this.extractedNumber = extractedNumber;
    }

    public int getExtractedNumber() {
        return extractedNumber;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setExtractedNumber(int extractedNumber) {
        this.extractedNumber = extractedNumber;
    }
}
