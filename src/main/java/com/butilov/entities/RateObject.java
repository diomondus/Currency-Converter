package com.butilov.entities;

import com.butilov.services.RatesDeserializerService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Dmitry Butilov
 * on 09.02.18.
 */
@AllArgsConstructor
@JsonDeserialize(using = RatesDeserializerService.class)
public class RateObject {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double rate;
}