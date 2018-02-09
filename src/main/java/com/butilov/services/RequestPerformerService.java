package com.butilov.services;

import com.butilov.entities.ApiResponse;
import com.butilov.entities.RateObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dmitry Butilov
 * on 10.02.18.
 */
public class RequestPerformerService {
    private static final String API_URL = "http://api.fixer.io/latest?base=%1$s&symbols=%2$s";
    private static final String DATA_FROM_URL = "Data from api.fixer.io";
    private static final String WRONG_URL = "Wrong url";
    private static final String ERROR = "Error";

    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(RateObject.class, new RatesDeserializerService())
            .create();

    public static ApiResponse performGetRequest(String fromCurrency, String toCurrency) {
        URL url;

        try {
            url = new URL(String.format(API_URL, fromCurrency, toCurrency));
        } catch (MalformedURLException exception) {
            System.err.println(WRONG_URL);
            return null;
        }
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.connect();

            int status = connection.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    ApiResponse apiResponse = gson.fromJson(br, ApiResponse.class);
                    br.close();
                    System.out.println("\n" + DATA_FROM_URL);
                    return apiResponse;
                default:
                    return null;
            }

        } catch (IOException exception) {
            System.err.println(ERROR);
            return null;
        }
    }
}
