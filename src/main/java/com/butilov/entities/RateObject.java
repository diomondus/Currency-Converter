package com.butilov.entities;

import lombok.Getter;

/**
 * Created by Dmitry Butilov
 * on 09.02.18.
 */
public class RateObject {

    @Getter
    private String name;
    @Getter
    private double rate;

    public RateObject(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }
}