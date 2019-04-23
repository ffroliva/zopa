package br.com.ffroliva.zopa.homework.service;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.domain.Lender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static br.com.ffroliva.zopa.homework.service.CompoundInterestService._36_MONTHS;
import static br.com.ffroliva.zopa.homework.service.FileUtils.MARKET_FILE_PATH;
import static br.com.ffroliva.zopa.homework.service.FileUtils.getMarketFileResource;

public class CompoundInterestServiceTest {

    private static final BigDecimal PRINCIPLE = BigDecimal.valueOf(1000);

    @Test
    public void testCalculateMonthlyRepayment() throws QuoteException {
        final BigDecimal interestRate =  CompoundInterestService.get().searchBestAverageInterestRate(PRINCIPLE.intValue(), getLenders());
        Assertions.assertEquals(BigDecimal.valueOf(30.88), CompoundInterestService.get().calculateMonthlyRepayment(PRINCIPLE, _36_MONTHS,interestRate));
    }

    @Test
    public void testSearchBestAverageInterestRate() throws QuoteException {
        final BigDecimal rate = CompoundInterestService.get().searchBestAverageInterestRate(PRINCIPLE.intValue(), getLenders());
        Assertions.assertEquals(BigDecimal.valueOf(0.070).setScale(3), rate);

    }

    @Test
    public void testCalculateTotalRepayment(){
        Assertions.assertEquals(BigDecimal.valueOf(1111.68) ,
                CompoundInterestService.get().calculateTotalRepayment(PRINCIPLE, _36_MONTHS,BigDecimal.valueOf(0.07)));
    }

    private List<Lender> getLenders() throws QuoteException {
        return FileService.get().extractLendersFromFile(getMarketFileResource(MARKET_FILE_PATH));
    }

}