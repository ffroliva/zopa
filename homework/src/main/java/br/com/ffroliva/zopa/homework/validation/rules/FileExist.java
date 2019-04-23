package br.com.ffroliva.zopa.homework.validation.rules;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.validation.ValidationRule;

import java.io.File;

public class FileExist implements ValidationRule {

    private String filePath;

    private FileExist(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute() throws QuoteException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new QuoteException(String.format("The informed path '%s' is not a valid file path.", filePath));
        }
    }

    public static FileExist of(String filePath) {
        return new FileExist(filePath);
    }
}
