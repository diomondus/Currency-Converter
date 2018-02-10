package com.butilov.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Dmitry Butilov
 * on 10.02.18.
 */
@Service
public class CacheService {
    public String getDataFromFile(String fromCurrency, String toCurrency) {

        String cachedFileName = getCachedFileName(fromCurrency, toCurrency);
        Path path = Paths.get(cachedFileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println();
            return null;
        }
        return stringBuilder.toString();
    }

    public void saveDataToFile(String data, String fromCurrency, String toCurrency) throws IOException {

        String cachedFileName = getCachedFileName(fromCurrency, toCurrency);
        Path path = Paths.get(cachedFileName);
        Path parentPath = path.getParent();
        Path grandParentPath = parentPath.getParent();
        try {
            Files.createDirectory(grandParentPath);
        } catch (IOException ignore) {
        }
        try {
            Files.createDirectory(parentPath);
        } catch (IOException ignore) {
        }
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write(data);
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
