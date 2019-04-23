package br.com.ffroliva.zopa.homework.validation.rules;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.validation.ValidationRule;

public class MinimumArgs implements ValidationRule {

    private String[] args;
    private int minimum;

    private MinimumArgs(String[] args, int minimum) {
        this.args = args;
        this.minimum = minimum;
    }

    @Override
    public void execute() throws QuoteException {
        if (args.length < minimum) {
            throw new QuoteException("Please inform a market.cvs file and your desired loan amount.");
        }
    }

    public static MinimumArgs of(String[] args, int minimum){
        return new MinimumArgs(args, minimum);
    }
}
