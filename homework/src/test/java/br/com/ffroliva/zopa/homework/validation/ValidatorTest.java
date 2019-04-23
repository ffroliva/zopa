package br.com.ffroliva.zopa.homework.validation;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.service.FileUtils;
import br.com.ffroliva.zopa.homework.validation.rules.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static br.com.ffroliva.zopa.homework.service.FileUtils.MARKET_FILE_PATH;

public class ValidatorTest {

    @Test
    public void testQuoteArgsValidator(){
        Assertions.assertThrows(QuoteException.class,
                () ->  Validator.validate(MinimumArgs.of(new String[]{}, 2)));

        Assertions.assertDoesNotThrow(
                () ->  Validator.validate(MinimumArgs.of(new String[]{"market.csv","1000"}, 2)));
    }

    @Test
    public void testLoanInRange(){
        Assertions.assertThrows(QuoteException.class,
                () ->  Validator.validate(LoanInRange.of(900,1000,15000,100)));

        Assertions.assertDoesNotThrow(
                () ->  Validator.validate(LoanInRange.of(1000,1000,15000,100)));
    }

    @Test
    public void testRequestedAmountUnavailable(){
        Assertions.assertThrows(QuoteException.class,
                () ->  Validator.validate(RequestedAmountUnavailable.of(1000, 900D)));

        Assertions.assertDoesNotThrow(
                () ->  Validator.validate(RequestedAmountUnavailable.of(1000,1000D)));
    }

    @Test
    public void testLoanObeyIncrementFactor(){
        Assertions.assertThrows(QuoteException.class,
                () ->  Validator.validate(LoanObeyIncrementFactor.of(1001, 100)));

        Assertions.assertDoesNotThrow(
                () ->  Validator.validate(LoanObeyIncrementFactor.of(1000,100)));
    }

    @Test
    public void testIsNumeric(){
        Assertions.assertThrows(QuoteException.class,
                () ->  Validator.validate(IsNumeric.of("aaa")));

        Assertions.assertDoesNotThrow(
                () ->  Validator.validate(IsNumeric.of("1")));
    }

    @Test
    public void testFileExist() {
        Assertions.assertThrows(QuoteException.class,
                () ->  Validator.validate(FileExist.of("test")));


        Assertions.assertDoesNotThrow(
                () ->  Validator.validate(FileExist.of(FileUtils.getMarketFileResource(MARKET_FILE_PATH))));
    }
}

