package com.butilov.services;

import com.butilov.entities.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by Dmitry Butilov
 * on 10.02.18.
 */
@Service
public class RESTClient {

    private static final String SERVICE_URL = "http://api.fixer.io/";
    private static final String LATEST_RESOURCE = "latest";
    private static final String LATEST_RESOURCE_BASE = "base";
    private static final String LATEST_RESOURCE_SYMBOLS = "symbols";

    public ApiResponse getResponce(String fromCurrency, String toCurrency) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVICE_URL + LATEST_RESOURCE + "?" + LATEST_RESOURCE_BASE +
                "=" + fromCurrency + "&" + LATEST_RESOURCE_SYMBOLS + "=" + toCurrency;
        return restTemplate.getForObject(url, ApiResponse.class);
    }
}
