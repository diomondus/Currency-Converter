package com.butilov.services;

import com.butilov.entities.ApiResponse;
import com.butilov.entities.RateObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Dmitry Butilov
 * on 10.02.18.
 */
@Service
public class RESTClient {

    private static final int CS_CREATED_HTTP_STATUS = 201;
    private static final int CS_OK_HTTP_STATUS = 200;

    private static final String GET_REQUEST = "GET";
    private static final String SERVICE_URL = "http://api.fixer.io/";
    private static final String LATEST_RESOURCE = "latest";
    private static final String LATEST_RESOURCE_BASE = "base";
    private static final String LATEST_RESOURCE_SYMBOLS = "symbols";

    @Autowired
    public RESTClient(RatesDeserializerService ratesDeserializerService) {
        gson = new GsonBuilder()
                .registerTypeAdapter(RateObject.class, ratesDeserializerService)
                .create();
    }

    public ApiResponse getResponce(String aFrom, String aTo) throws IOException {
        HttpURLConnection connection = createGETConnection(aFrom, aTo);

        ApiResponse apiResponce = null;
        int status = connection.getResponseCode();

        if (status == CS_CREATED_HTTP_STATUS || status == CS_OK_HTTP_STATUS) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            apiResponce = gson.fromJson(reader, ApiResponse.class);
        }

        connection.disconnect();
        return apiResponce;
    }

    private HttpURLConnection createGETConnection(String aFrom, String aTo) throws IOException {
        URL url = new URL(SERVICE_URL + LATEST_RESOURCE + "?" + LATEST_RESOURCE_BASE + "=" + aFrom + "&" + LATEST_RESOURCE_SYMBOLS + "=" + aTo);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(GET_REQUEST);

        connection.setConnectTimeout(3000);
        connection.setReadTimeout(3000);

        return connection;
    }

    private Gson gson;
}
