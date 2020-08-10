package com.datacollcet.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by King on 2018/8/7.
 */

public class MagReadings {
    private int id;
    private List<Float> mags = new ArrayList<>();

    public MagReadings(int id, List<Float> mags) {
        this.id = id;
        this.mags = mags;
    }

    public MagReadings(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Float> getMags() {
        return mags;
    }

    public void setMags(List<Float> mags) {
        this.mags = mags;
    }
}
