package com.butilov.entities;

import lombok.Getter;

/**
 * Created by Dmitry Butilov
 * on 09.02.18.
 */
public class ApiResponse {

    @Getter
    private String base;
    @Getter
    private String date;
    @Getter
    private RateObject rates;

    @Override
    public String toString() {
        return base + " => " + rates.getName() + " : " + rates.getRate();
    }
}