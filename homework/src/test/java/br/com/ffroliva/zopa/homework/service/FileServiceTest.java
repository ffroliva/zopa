package br.com.ffroliva.zopa.homework.service;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.domain.Lender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.ffroliva.zopa.homework.service.FileUtils.*;

class FileServiceTest {

    @Test
    void testReadFile() throws QuoteException {
        final String marketFilePath = getMarketFileResource(MARKET_FILE_PATH);
        Assertions.assertTrue(FileService.get().readLinesFromFile(marketFilePath).isPresent());
        Assertions.assertThrows(QuoteException.class,
                () -> FileService.get().readLinesFromFile(MARKET_FILE_PATH));
    }

    @Test
    void testExtractLendersFromFile() throws QuoteException {
        final List<Lender> lenders = FileService.get().extractLendersFromFile(getMarketFileResource(MARKET_FILE_PATH));
        Assertions.assertEquals(lenders.size(), 7);
        Assertions.assertThrows(QuoteException.class,
                () -> FileService.get().extractLendersFromFile(getMarketFileResource(INVALID_MARKET_FILE_PATH)));

    }

}
