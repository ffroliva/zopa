package br.com.ffroliva.zopa.homework.validation;

import br.com.ffroliva.zopa.homework.QuoteException;

public class Validator {

    public static void validate(ValidationRule rule) throws QuoteException {
        rule.execute();
    }
}
