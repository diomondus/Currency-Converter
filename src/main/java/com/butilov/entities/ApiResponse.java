package com.butilov.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Dmitry Butilov
 * on 09.02.18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class ApiResponse {

    @Getter
    @Setter
    private String base;
    @Getter
    @Setter
    private String date;
    @Getter
    @Setter
    private RateObject rates;

    @Override
    public String toString() {
        return base + " => " + rates.getName() + " : " + rates.getRate();
    }
}