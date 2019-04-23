package br.com.ffroliva.zopa.homework.service;

import br.com.ffroliva.zopa.homework.QuoteException;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class FileUtils {

    public static final String MARKET_FILE_PATH = "market.csv";
    public static final String INVALID_MARKET_FILE_PATH = "invalid_market.csv";

    public static String getMarketFileResource(String filePath) throws QuoteException {
        URI uri = null;
        try {
            uri = Thread.currentThread()
                    .getContextClassLoader()
                    .getResource(filePath)
                    .toURI();
        } catch (URISyntaxException e) {
            throw new QuoteException(e.getMessage());
        }
        return Paths.get(uri).toString();

    }
}
