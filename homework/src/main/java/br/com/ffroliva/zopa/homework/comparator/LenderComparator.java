package br.com.ffroliva.zopa.homework.comparator;

import br.com.ffroliva.zopa.homework.domain.Lender;

import java.util.Comparator;

public class LenderComparator implements Comparator<Lender> {

    @Override
    public int compare(Lender o1, Lender o2) {
        int rateCompare = Float.valueOf(o1.getRate()).compareTo(o2.getRate());
        if(rateCompare != 0){
            return rateCompare;
        }
        int availableCompare = Integer.valueOf(o1.getAvailable()).compareTo(o2.getAvailable());
        if(availableCompare == 1){
            return -1;
        } else {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
