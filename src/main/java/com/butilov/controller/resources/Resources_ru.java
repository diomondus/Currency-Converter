package com.butilov.controller.resources;

public class Resources_ru extends Resources {

    @Override
    protected Object[][] getContents() {
        return res;
    }

    private String res[][] = {
            {Constants.FROM_CURRENCY, "Из валюты:"},
            {Constants.TO_CURRENCY, "В валюту:"},
            {Constants.CACHE_READ_SUCCESS, "Данные из кеша"},
            {Constants.WEB_ACCESS_SUCCESS, "Данные из api.fixer.io"},
            {Constants.ERROR, "Ошибка"},
            {Constants.SAVE_FILE_ERROR, "Ошибка. Файл не сохранен"},
            {Constants.DIRECTORY_CREATION_ERROR, "Ошибка. Директория не сохранена"},
            {Constants.WRONG_CURRENCY_FORMAT_ERROR, "Ошибка. Неправильный формат валюты"},
    };
}
