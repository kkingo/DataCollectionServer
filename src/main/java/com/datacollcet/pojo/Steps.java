package com.datacollcet.pojo;



/**
 * Created by King on 2018/9/3.
 */

public class Steps {
    private int id;
    private int steps;
    private int fpid;

    public Steps(int id, int steps, int fpid) {
        this.id = id;
        this.steps = steps;
        this.fpid = fpid;
    }

    public Steps(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getFpid() {
        return fpid;
    }

    public void setFpid(int fpid) {
        this.fpid = fpid;
    }
}
