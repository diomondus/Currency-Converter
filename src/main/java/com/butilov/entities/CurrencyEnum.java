package com.butilov.entities;

/**
 * Created by Dmitry Butilov
 * on 09.02.18.
 */
public enum CurrencyEnum {
    AUD("AUD"),
    BGN("BGN"),
    BRL("BRL"),
    CAD("CAD"),
    CHF("CHF"),
    CNY("CNY"),
    CZK("CZK"),
    DKK("DKK"),
    GBP("GBP"),
    HKD("HKD"),
    HRK("HRK"),
    HUF("HUF"),
    IDR("IDR"),
    ILS("ILS"),
    INR("INR"),
    JPY("JPY"),
    KRW("KRW"),
    MXN("MXN"),
    MYR("MYR"),
    NOK("NOK"),
    NZD("NZD"),
    PHP("PHP"),
    PLN("PLN"),
    RON("RON"),
    RUB("RUB"),
    SEK("SEK"),
    SGD("SGD"),
    THB("THB"),
    TRY("TRY"),
    USD("USD"),
    ZAR("ZAR"),
    EUR("EUR");

    public static boolean isValidCurrency(String currency) {
        try {
            CurrencyEnum.valueOf(currency);
        } catch (IllegalArgumentException exception) {
            return false;
        }
        return true;
    }

    private String text;

    CurrencyEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}