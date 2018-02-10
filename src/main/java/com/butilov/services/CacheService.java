package com.butilov.services;

import com.butilov.StringConstants;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Dmitry Butilov
 * on 10.02.18.
 */
@Service
public class CacheService {
    public String getDataFromFile(String fromCurrency, String toCurrency) {
        BufferedReader bufferedReader;
        try {
            String cachedFileName = getCachedFileName(fromCurrency, toCurrency);
            bufferedReader = new BufferedReader(new FileReader(cachedFileName));

            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                sb.append(line);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            return sb.toString();
        } catch (IOException exception) {
            return null;
        }
    }

    public void saveDataToFile(String data, String fromCurrency, String toCurrency) {
        PrintWriter printWriter = null;
        String cachedFileName = getCachedFileName(fromCurrency, toCurrency);
        File file = new File(cachedFileName);

        try {
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                throw new IllegalStateException(StringConstants.DIRECTORY_CREATION_ERROR);
            }
            printWriter = new PrintWriter(cachedFileName);
            printWriter.println(data);
        } catch (FileNotFoundException exception) {
            System.err.println(StringConstants.SAVE_FILE_ERROR);
        } catch (IllegalStateException exception) {
            System.err.println(exception.getMessage());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    private String getCachedFileName(String fromCurrency, String toCurrency) {
        return "cache/" + getCurrentDate() + "/" + fromCurrency + "-" + toCurrency + ".txt";
    }

    private String getCurrentDate() {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("CET"));
        int cetHour = instance.get(Calendar.HOUR_OF_DAY);
        if (cetHour < 16) {
            instance.add(Calendar.DATE, -1);
        }
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DAY_OF_MONTH);

        String sMonth = month < 10 ? "0" + month : String.valueOf(month);
        String sDay = day < 10 ? "0" + day : String.valueOf(day);

        return year + "-" + sMonth + "-" + sDay;
    }
}
