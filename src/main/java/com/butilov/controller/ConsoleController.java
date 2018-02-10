package com.butilov.controller;

import com.butilov.StringConstants;
import com.butilov.entities.ApiResponse;
import com.butilov.entities.CurrencyEnum;
import com.butilov.services.CacheService;
import com.butilov.services.RESTClient;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Dmitry Butilov
 * on 10.02.18.
 */
@Controller
public class ConsoleController {
    public ConsoleController(CacheService cacheService, RESTClient RESTClient) {
        mCacheService = cacheService;
        mRESTClient = RESTClient;
    }

    private String inputCurrency(String displayText) {
        Scanner scanner = new Scanner(System.in);
        String currency;

        do {
            System.out.println(displayText);
            currency = scanner.nextLine().toUpperCase();

            if (!CurrencyEnum.isValidCurrency(currency)) {
                System.err.println(StringConstants.WRONG_CURRENCY_FORMAT_ERROR);
            }
        } while (!CurrencyEnum.isValidCurrency(currency));

        return currency;
    }

    public void convert() {
        final String fromCurrency = inputCurrency(StringConstants.FROM_CURRENCY);
        final String toCurrency = inputCurrency(StringConstants.TO_CURRENCY);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            String cachedData = mCacheService.getDataFromFile(fromCurrency, toCurrency);
            if (cachedData != null) {
                System.out.println("\n" + StringConstants.CACHE_READ_SUCCESS);
                System.out.println(cachedData);
            } else {
                ApiResponse apiResponse = null;
                try {
                    apiResponse = mRESTClient.getResponce(fromCurrency, toCurrency);
                } catch (IOException ex) {
                    System.out.println(StringConstants.ERROR);
                }
                if (apiResponse != null) {
                    System.out.println("\n" + StringConstants.WEB_ACCESS_SUCCESS);
                    System.out.println(apiResponse.toString());
                    mCacheService.saveDataToFile(apiResponse.toString(), fromCurrency, toCurrency);
                }
            }
        });
        executorService.shutdown();
    }

    private CacheService mCacheService;

    private RESTClient mRESTClient;
}
