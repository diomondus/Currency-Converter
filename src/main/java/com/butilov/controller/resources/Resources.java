package com.butilov.controller.resources;

import java.util.ListResourceBundle;

public class Resources extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return res;
    }

    private String res[][] = {
            {Constants.FROM_CURRENCY, "From currency"},
            {Constants.TO_CURRENCY, "To currency"},
            {Constants.CACHE_READ_SUCCESS, "Data from cache"},
            {Constants.WEB_ACCESS_SUCCESS, "Data from api.fixer.io"},
            {Constants.ERROR, "Error"},
            {Constants.SAVE_FILE_ERROR, "Error. File not saved"},
            {Constants.DIRECTORY_CREATION_ERROR, "Error. Directory not created"},
            {Constants.WRONG_CURRENCY_FORMAT_ERROR, "Error. Wrong currency format"},
    };
}