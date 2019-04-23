package br.com.ffroliva.zopa.homework.validation.rules;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.validation.ValidationRule;

public class LoanObeyIncrementFactor implements ValidationRule {

    private final int loanAmount;
    private final int incrementFactor;

    public LoanObeyIncrementFactor(int loanAmount, int incrementFactor) {
        this.loanAmount = loanAmount;
        this.incrementFactor = incrementFactor;
    }

    @Override
    public void execute() throws QuoteException {
        if(!loadObeyIncrementFactor(loanAmount, incrementFactor)) {
             throw new QuoteException(String.format("Loan amount informed should have an increment factor of £%s.", incrementFactor));
        }
    }

    private boolean loadObeyIncrementFactor(int loanAmount, int incrementRange) {
        return loanAmount % incrementRange == 0;
    }

    public static LoanObeyIncrementFactor of(int loanAmount, int incrementFactor) {
        return new LoanObeyIncrementFactor(loanAmount, incrementFactor);
    }

}
