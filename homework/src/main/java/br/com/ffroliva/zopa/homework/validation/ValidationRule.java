package br.com.ffroliva.zopa.homework.validation;

import br.com.ffroliva.zopa.homework.QuoteException;

public interface ValidationRule {

    void execute() throws QuoteException;
}
