package com.butilov.services;

import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by Dmitry Butilov
 * on 10.02.18.
 */
@Service
public class CacheService {
    private static final String CACHE_READ_SUCCESS = "Data from cache";
    private static final String SAVE_FILE_ERROR = "Error. File not saved.";
    private static final String DIRECTORY_CREATION_ERROR = "Error. Directory not created.";

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
            System.out.println("\n" + CACHE_READ_SUCCESS);
            return sb.toString();
        } catch (IOException exception) {
            return null;
        }
    }

    public void saveDataToFile(String data, String fromCurrency, String toCurrency) {
        PrintWriter printWriter = null;

        try {
            File file = new File(getCachedFileName(fromCurrency, toCurrency));
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                throw new IllegalStateException(DIRECTORY_CREATION_ERROR);
            }
            printWriter = new PrintWriter(getCachedFileName(fromCurrency, toCurrency));
            printWriter.println(data);
        } catch (FileNotFoundException exception) {
            System.err.println(SAVE_FILE_ERROR);
        } catch (IllegalStateException exception) {
            System.err.println(exception.getMessage());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    private String getCachedFileName(String fromCurrency, String toCurrency) {
        return "cache/" + fromCurrency + "-" + toCurrency + ".txt";
    }
}
