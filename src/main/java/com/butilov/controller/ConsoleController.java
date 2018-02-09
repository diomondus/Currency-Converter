package com.butilov.controller;

import com.butilov.entities.CurrencyEnum;

import java.util.Scanner;

/**
 * Created by Dmitry Butilov
 * on 10.02.18.
 */
public class ConsoleController {
    private static final String WRONG_CURRENCY_FORMAT_ERROR = "Error. Wrong currency format";

    public static String enterCurrency(String displayText) {
        Scanner scanner = new Scanner(System.in);
        String currency;

        do {
            System.out.println(displayText);
            currency = scanner.nextLine().toUpperCase();

            if (!CurrencyEnum.isValidCurrency(currency)) {
                System.err.println(WRONG_CURRENCY_FORMAT_ERROR);
            }
        } while (!CurrencyEnum.isValidCurrency(currency));

        return currency;
    }
}
