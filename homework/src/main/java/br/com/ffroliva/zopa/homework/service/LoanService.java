package br.com.ffroliva.zopa.homework.service;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.domain.Lender;

import java.util.List;

public class LoanService {

    public static final String LOAN_AMOUNT_PARSER_EXCEPTION = "Provided input %s is not a valid loan. " +
            "Please provide a loan amount between %s and %s with any %s increment.";
    public static int MINIMUM_LOAN = 1000;
    public static int MAXIMUM_LOAN = 15000;
    public static int INCREMENT_FACTOR = 100;

    private static LoanService instance;

    private LoanService(){}

    public Integer parseLoanAmount(String loanAmount) throws QuoteException {
        try {
            return Integer.valueOf(loanAmount);
        } catch (NumberFormatException e) {
            throw new QuoteException(String.format(LOAN_AMOUNT_PARSER_EXCEPTION,
                    loanAmount,
                    MINIMUM_LOAN,
                    MAXIMUM_LOAN,
                    INCREMENT_FACTOR));
        }
    }

    public boolean isLoanInRange(int loanAmount, int min, int max) {
        return loanAmount >= min && loanAmount <= max;
    }

    public Double getAvailableMoney(List<Lender> lenders){
        return lenders.stream().mapToDouble(Lender::getAvailable).sum();
    }

    public boolean isRequestedAmountUnavailable(int requestedAmount, double availableMoney){
        return availableMoney < requestedAmount;
    }

    public static LoanService get() {
        if (instance == null) {
            instance = new LoanService();
        }
        return instance;
    }

}
