package br.com.ffroliva.zopa.homework.validation.rules;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.service.LoanService;
import br.com.ffroliva.zopa.homework.validation.ValidationRule;

import static br.com.ffroliva.zopa.homework.service.LoanService.MAXIMUM_LOAN;
import static br.com.ffroliva.zopa.homework.service.LoanService.MINIMUM_LOAN;

public class LoanInRange implements ValidationRule {

    private int loanAmount;
    private int minimumLoan;
    private int maximumLoan;
    private int incrementFactor;

    private LoanInRange(int loanAmount, int minimumLoan, int maximumLoan, int incrementFactor) {
        this.loanAmount = loanAmount;
        this.minimumLoan = minimumLoan;
        this.maximumLoan = maximumLoan;
        this.incrementFactor = incrementFactor;
    }

    @Override
    public void execute() throws QuoteException {
        if (!LoanService.get().isLoanInRange(loanAmount, MINIMUM_LOAN, MAXIMUM_LOAN)) {
            throw new QuoteException(String.format("Loan Amount should be between £%s and £%s with increments of £%s.",
                    minimumLoan, maximumLoan, incrementFactor));
        }
    }

    public static LoanInRange of(int loanAmount, int minimumLoan, int maximumLoan, int incrementFactor) {
        return new  LoanInRange(loanAmount, minimumLoan, maximumLoan, incrementFactor);
    }
}
