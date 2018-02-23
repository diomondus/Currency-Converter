package com.butilov.entities;

import com.butilov.services.RatesDeserializerService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by Dmitry Butilov
 * on 09.02.18.
 */
@JsonDeserialize(using = RatesDeserializerService.class)
public class RateObject {

    private String name;
    private double rate;

    public RateObject(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return this.name;
    }

    public double getRate() {
        return this.rate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}