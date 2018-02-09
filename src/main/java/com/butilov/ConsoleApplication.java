package com.butilov;

import com.butilov.controller.ConsoleController;
import com.butilov.entities.ApiResponse;
import com.butilov.services.CacheService;
import com.butilov.services.RequestPerformerService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Dmitry Butilov
 * on 09.02.18.
 */
public class ConsoleApplication {
    private static final String FROM_CURRENCY = "From currency:";
    private static final String TO_CURRENCY = "To currency:";
    private static final String ERROR = "Error";

    public static void main(String[] args) {

        final String fromCurrency = ConsoleController.enterCurrency(FROM_CURRENCY);
        final String toCurrency = ConsoleController.enterCurrency(TO_CURRENCY);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            String cachedData = CacheService.getDataFromFile(fromCurrency, toCurrency);
            if (cachedData != null) { // todo сделать проверку на сегодняшнее число
                System.out.println(cachedData);
            } else {
                ApiResponse apiResponse = RequestPerformerService.performGetRequest(fromCurrency, toCurrency);
                if (apiResponse != null) {
                    System.out.println(apiResponse.toString());
                    CacheService.saveDataToFile(apiResponse.toString(), fromCurrency, toCurrency);
                } else {
                    System.out.println(ERROR);
                }
            }
        });
        executorService.shutdown();
    }
}