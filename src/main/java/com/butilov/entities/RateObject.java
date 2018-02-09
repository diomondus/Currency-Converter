package com.butilov.entities;

/**
 * Created by Dmitry Butilov
 * on 09.02.18.
 */
public class RateObject {

    private String name;
    private double rate;

    public RateObject(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    String getName() {
        return name;
    }

    double getRate() {
        return rate;
    }
}