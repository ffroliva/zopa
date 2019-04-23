package br.com.ffroliva.zopa.homework;

import br.com.ffroliva.zopa.homework.service.FileUtils;
import br.com.ffroliva.zopa.homework.service.LoanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuoteAppTest {

    @Test
    public void testQuoteValidParams() throws QuoteException {
        final String marketFileResource = FileUtils.getMarketFileResource(FileUtils.MARKET_FILE_PATH);
        String[] args = new String[] {marketFileResource, String.valueOf(LoanService.MINIMUM_LOAN)};
        QuoteApp.main(args);
    }

    @Test
    public void testQuoteInvalidFilePath()  {
        String[] args = new String[] {"aaaa", String.valueOf(LoanService.MINIMUM_LOAN)};
        Assertions.assertDoesNotThrow(() -> QuoteApp.main(args));
    }

    @Test
    public void testQuoteLoanBellowMinimum() throws QuoteException {
        final String marketFileResource = FileUtils.getMarketFileResource(FileUtils.MARKET_FILE_PATH);
        String[] args = new String[] {marketFileResource, "900"};
        Assertions.assertDoesNotThrow(() -> QuoteApp.main(args));
    }

    @Test
    public void testQuoteLoanNotANumber() throws QuoteException {
        final String marketFileResource = FileUtils.getMarketFileResource(FileUtils.MARKET_FILE_PATH);
        String[] args = new String[] {marketFileResource, "bbbb"};
        Assertions.assertDoesNotThrow(() -> QuoteApp.main(args));
    }
}
