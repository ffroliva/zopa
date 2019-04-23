package br.com.ffroliva.zopa.homework.domain;

import br.com.ffroliva.zopa.homework.comparator.LenderComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LenderTest {

    private static Lender BOB1 = new Lender.LenderBuilder().name("Bob").rate(0.01f).available(100).build();
    private static Lender BOB2 = new Lender.LenderBuilder().name("Bob").rate(0.02f).available(100).build();
    private static Lender BOB3 = new Lender.LenderBuilder().name("Bob").rate(0.02f).available(200).build();

    @Test
    public void testLenderComparator(){
        final List<Lender> lenders = Arrays.asList(BOB2, BOB1, BOB3);
        Assertions.assertEquals(BOB2, lenders.get(0));
        Collections.sort(lenders, new LenderComparator());
        Assertions.assertEquals(BOB2, lenders.get(2));
    }
}
