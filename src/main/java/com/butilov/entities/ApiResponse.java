package com.butilov.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Dmitry Butilov
 * on 09.02.18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {

    private String base;
    private String date;
    private RateObject rates;

    public ApiResponse() {
    }

    @Override
    public String toString() {
        return base + " => " + rates.getName() + " : " + rates.getRate();
    }

    public String getBase() {
        return this.base;
    }

    public String getDate() {
        return this.date;
    }

    public RateObject getRates() {
        return this.rates;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRates(RateObject rates) {
        this.rates = rates;
    }
}