package br.com.ffroliva.zopa.homework.service;

import br.com.ffroliva.zopa.homework.QuoteException;
import br.com.ffroliva.zopa.homework.comparator.LenderComparator;
import br.com.ffroliva.zopa.homework.domain.Lender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FileService {

    private static FileService instance;

    private FileService(){
    }

    protected Optional<Stream<String>> readLinesFromFile(String marketFile) {
        Optional<Stream<String>> lines = Optional.empty();
        try {
            lines = Optional.of(Files.lines(Paths.get(marketFile)));
        } catch (IOException e) {
            new QuoteException(String.format("Error processing market file: %s", marketFile));
        }
        return lines;
    }

    public List<Lender> extractLendersFromFile(String marketFile) throws QuoteException {
        Stream<String> lines = readLinesFromFile(marketFile).orElseGet(() -> Stream.empty());
        try {
            return  lines.skip(1)
                    .map(l -> l.split(",")) // first line is skipped because it is a header
                    .map(Lender::createLender)
                    .sorted(new LenderComparator())
                    .collect(toList());
        } catch(Exception e) {
            throw new QuoteException("Unable to extract the lenders from file. Please verify if it is correctly formated");
        }
    }

    public static FileService get() {
        if(instance == null){
            instance = new FileService();
        }
        return instance;
    }
}
