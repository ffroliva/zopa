package br.com.ffroliva.zopa.homework.validation.rules;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.validation.ValidationRule;

public class IsNumeric implements ValidationRule {

    private String loanAmount;

    private IsNumeric(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Override
    public void execute() throws QuoteException {
        try {
            Integer.valueOf(loanAmount);
        } catch (NumberFormatException e) {
            throw new QuoteException(String.format("Loan Amount '%s' is not a number.",loanAmount));
        }
    }

    public static IsNumeric of(String loanAmount) {
        return new IsNumeric(loanAmount);
    }
}
