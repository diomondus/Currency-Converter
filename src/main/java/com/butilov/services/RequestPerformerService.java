package com.butilov.services;

import com.butilov.StringConstants;
import com.butilov.entities.ApiResponse;
import com.butilov.entities.RateObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

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
@Service
public class RequestPerformerService {
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(RateObject.class, new RatesDeserializerService())
            .create();

    public ApiResponse performGetRequest(String fromCurrency, String toCurrency) {
        URL url;

        try {
            url = new URL(String.format(StringConstants.API_URL, fromCurrency, toCurrency));
        } catch (MalformedURLException exception) {
            System.err.println(StringConstants.WRONG_URL);
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
                    System.out.println("\n" + StringConstants.DATA_FROM_URL);
                    return apiResponse;
                default:
                    return null;
            }

        } catch (IOException exception) {
            System.err.println(StringConstants.ERROR);
            return null;
        }
    }
}
