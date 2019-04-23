package br.com.ffroliva.zopa.homework.service;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.domain.Lender;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ROUND_HALF_EVEN;

public class CompoundInterestService {

    public static final BigDecimal _36_MONTHS = BigDecimal.valueOf(36);
    public static final BigDecimal MONTHS_PER_YEAR = BigDecimal.valueOf(12);
    public static final int SCALE = BigInteger.TEN.intValue();
    public static final int ROUND_UP = BigDecimal.ROUND_UP;

    private static CompoundInterestService instance;

    private CompoundInterestService() {
    }

    /**
     * Formula: M = P * ( J / (1 - (1 + J)^ -N))
     * @return monthRepayment
     */
    public BigDecimal calculateMonthlyRepayment(BigDecimal loanAmount, BigDecimal termInMonths, BigDecimal annualInterestRate) {
        BigDecimal periodicInterestRate = annualInterestRate.divide(MONTHS_PER_YEAR,SCALE,ROUND_UP);  // J
        BigDecimal a = ONE.add(periodicInterestRate); // (1+J)
        BigDecimal b = new BigDecimal(Math.pow(a.doubleValue(), termInMonths.negate().intValue())); // (1 + J) ^ -N
        BigDecimal c = ONE.subtract(b); //1 - (1 + J) ^ N
        BigDecimal e = periodicInterestRate.divide(c,SCALE, ROUND_HALF_EVEN); // J /  1 - (1 + J) ^ N
        BigDecimal monthRepayment = loanAmount
                .multiply(e)
                .setScale(2,RoundingMode.HALF_EVEN); // P * ( J / (1 - (1 + J)^ -N))
        return monthRepayment;

    }

    public BigDecimal calculateTotalRepayment(BigDecimal loanAmount, BigDecimal termInMonths, BigDecimal annualInterestRate) {
        return calculateMonthlyRepayment(loanAmount, termInMonths, annualInterestRate)
                .multiply(termInMonths)
                .setScale(2,RoundingMode.HALF_EVEN);
    }

    public BigDecimal searchBestAverageInterestRate(int principal, List<Lender> lenders) throws QuoteException {
        Map<Float, Long> availableInterestRates = availableInterestRates(lenders);
        List<Float> keys = availableInterestRates.keySet().stream().sorted().collect(Collectors.toList());
        BigDecimal availableSum = BigDecimal.valueOf(availableInterestRates.get(keys.get(0)));
        BigDecimal rateSum = BigDecimal.valueOf(keys.get(0));
        for (int i = 1; i < keys.size(); i++) {
            if (availableSum.intValue() >= principal) {
                return rateSum.divide(BigDecimal.valueOf(i),3,ROUND_HALF_EVEN);
            }
            availableSum = availableSum.add(BigDecimal.valueOf(availableInterestRates.get(keys.get(i))));
            rateSum = rateSum.add(BigDecimal.valueOf(keys.get(i)));
        }
        throw new QuoteException("Unable to find a the best interest rate for the given principal and list of lenders");
    }

    public Map<Float, Long> availableInterestRates(List<Lender> lenders) {
        Map<Float, Long> mapRatesAvailableSum = new HashMap<>();
        lenders.stream().forEach(lender -> {
            if (mapRatesAvailableSum.containsKey(lender.getRate())){
                mapRatesAvailableSum.put(lender.getRate(),
                        mapRatesAvailableSum.get(lender.getRate()) + lender.getAvailable());
            } else {
                mapRatesAvailableSum.put(lender.getRate(),Long.valueOf(lender.getAvailable()));
            }
        });
        return mapRatesAvailableSum;
    }

    public static CompoundInterestService get() {
        if( instance == null) {
            instance = new CompoundInterestService();
        }
        return instance;
    }

}
