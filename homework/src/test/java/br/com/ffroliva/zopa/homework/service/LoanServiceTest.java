package br.com.ffroliva.zopa.homework.service;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.domain.Lender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static br.com.ffroliva.zopa.homework.service.LoanService.*;

public class LoanServiceTest {

    private static final String VALID_LOAN_AMOUNT = "1000";
    private static final String DOUBLE_LOAN_AMOUNT = "1000.23";
    private static final String INVALID_LOAN_AMOUNT = "NaN";

    @Test
    public void testLoadInRange() {
        Assertions.assertTrue(LoanService.get().isLoanInRange(1000, MINIMUM_LOAN, MAXIMUM_LOAN));
        Assertions.assertTrue(LoanService.get().isLoanInRange(15000, MINIMUM_LOAN, MAXIMUM_LOAN));
        Assertions.assertFalse(LoanService.get().isLoanInRange(0, MINIMUM_LOAN, MAXIMUM_LOAN));
        Assertions.assertFalse(LoanService.get().isLoanInRange(15100, MINIMUM_LOAN, MAXIMUM_LOAN));
    }

    public void testParseValidLoanAmount() throws QuoteException {
        Assertions.assertEquals(LoanService.get().parseLoanAmount(VALID_LOAN_AMOUNT), Integer.parseInt(VALID_LOAN_AMOUNT));
    }

    @Test
    public void testInvalidLoanAmount() throws QuoteException {
        QuoteException ex;

        ex = Assertions.assertThrows(QuoteException.class,
                () -> LoanService.get().parseLoanAmount(DOUBLE_LOAN_AMOUNT));
        Assertions.assertEquals(ex.getMessage(), String.format(LOAN_AMOUNT_PARSER_EXCEPTION,
                                                           DOUBLE_LOAN_AMOUNT,
                                                           MINIMUM_LOAN,
                                                           MAXIMUM_LOAN,
                                                           INCREMENT_FACTOR));

        ex = Assertions.assertThrows(QuoteException.class,
                () -> LoanService.get().parseLoanAmount(INVALID_LOAN_AMOUNT));
        Assertions.assertEquals(ex.getMessage(), String.format(LOAN_AMOUNT_PARSER_EXCEPTION,
                                                           INVALID_LOAN_AMOUNT,
                                                           MINIMUM_LOAN,
                                                           MAXIMUM_LOAN,
                                                           INCREMENT_FACTOR));
    }

    @Test
    public void testAvailableMoney(){
        List<Lender> lenders = getLenders();
        Assertions.assertEquals(20D,LoanService.get().getAvailableMoney(lenders));
    }

    @Test
    public void testIfRequestedAmountIsUnavailable(){
        final Double availableMoney = LoanService.get().getAvailableMoney(getLenders());
        Assertions.assertTrue(LoanService.get().isRequestedAmountUnavailable(30,availableMoney));
    }

    private List<Lender> getLenders(){
        Lender Lender1 = Lender.builder().available(10).build();
        Lender Lender2 = Lender.builder().available(10).build();
        return Arrays.asList(Lender1, Lender2);
    }

}
