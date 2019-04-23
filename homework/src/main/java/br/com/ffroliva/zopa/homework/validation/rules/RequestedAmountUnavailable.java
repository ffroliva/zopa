package br.com.ffroliva.zopa.homework.validation.rules;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.service.LoanService;
import br.com.ffroliva.zopa.homework.validation.ValidationRule;

public class RequestedAmountUnavailable implements ValidationRule {

    private final Integer loanAmount;
    private final Double availableMoney;

    public RequestedAmountUnavailable(Integer loanAmount, Double availableMoney) {
        this.loanAmount = loanAmount;
        this.availableMoney = availableMoney;
    }

    public static RequestedAmountUnavailable of(Integer loanAmount, Double availableMoney) {
        return new RequestedAmountUnavailable(loanAmount, availableMoney);
    }

    @Override
    public void execute() throws QuoteException {
        if (LoanService.get().isRequestedAmountUnavailable(loanAmount, availableMoney)) {
            throw new QuoteException("It is not possible to provide a quote at this time. " +
                    "The market does not have sufficient offers from lenders to satisfy the loan. ");
        }
    }
}
