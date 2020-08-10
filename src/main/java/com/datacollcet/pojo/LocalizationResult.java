package com.datacollcet.pojo;

import java.util.HashMap;
import java.util.Map;

public class LocalizationResult {
    Map<Integer, Double> relatives = new HashMap<>();
    Subarea subarea = new Subarea();

    public LocalizationResult(){}
    public LocalizationResult( Map<Integer, Double> relatives, Subarea subarea) {
        this.relatives = relatives;
        this.subarea = subarea;
    }

    public Subarea getSubarea() {
        return subarea;
    }

    public void setSubarea(Subarea subarea) {
        this.subarea = subarea;
    }

    public Map<Integer, Double> getRelatives() {
        return relatives;
    }

    public void setRelatives(Map<Integer, Double> relatives) {
        this.relatives = relatives;
    }
}
