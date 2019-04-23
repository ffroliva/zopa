package br.com.ffroliva.zopa.homework;

import br.com.ffroliva.zopa.homework.domain.Lender;
import br.com.ffroliva.zopa.homework.service.CompoundInterestService;
import br.com.ffroliva.zopa.homework.service.FileService;
import br.com.ffroliva.zopa.homework.service.LoanService;
import br.com.ffroliva.zopa.homework.validation.Validator;
import br.com.ffroliva.zopa.homework.validation.rules.*;

import java.math.BigDecimal;
import java.util.List;

import static br.com.ffroliva.zopa.homework.service.CompoundInterestService._36_MONTHS;
import static br.com.ffroliva.zopa.homework.service.LoanService.*;

public class QuoteApp {

    private static final int MINIMUM_ARGS_INPUT = 2;

    public static void main(String[] args) {
        try {
            Validator.validate(MinimumArgs.of(args, MINIMUM_ARGS_INPUT));
            Validator.validate(FileExist.of(args[0]));
            Validator.validate(IsNumeric.of(args[1]));
            final Integer loanAmount = Integer.valueOf(args[1]);
            Validator.validate(LoanInRange.of(loanAmount, MINIMUM_LOAN, MAXIMUM_LOAN, INCREMENT_FACTOR));
            final List<Lender> lenders = FileService.get().extractLendersFromFile(args[0]);
            final Double availableMoney = LoanService.get().getAvailableMoney(lenders);
            Validator.validate(RequestedAmountUnavailable.of(loanAmount, availableMoney));
            Validator.validate(LoanObeyIncrementFactor.of(loanAmount,INCREMENT_FACTOR));
            final BigDecimal rate = CompoundInterestService.get().searchBestAverageInterestRate(loanAmount, lenders);
            final BigDecimal totalRepayment = CompoundInterestService.get().calculateTotalRepayment(BigDecimal.valueOf(loanAmount), _36_MONTHS, rate);
            BigDecimal monthlyPayment = CompoundInterestService.get().calculateMonthlyRepayment(BigDecimal.valueOf(loanAmount),_36_MONTHS,rate);
            printQuote(loanAmount, rate, monthlyPayment, totalRepayment);
        } catch (QuoteException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void printQuote(int loanAmount, BigDecimal rate, BigDecimal monthlyPayment, BigDecimal totalPayment) {
        System.out.println(String.format("Requested amount: £%s", loanAmount));
        System.out.println(new StringBuilder("Rate: ").append(rate).append("%"));
        System.out.println(String.format("Monthly repayment: £%s", monthlyPayment));
        System.out.println(String.format("Total repayment: £%s", totalPayment));
    }


}
