package br.com.ffroliva.zopa.homework.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Lender implements Serializable {

    private String name;
    private float rate;
    private int available;

    public static Lender createLender(String[] columns) {
        Lender lender = new LenderBuilder()
                .name(columns[0])
                .rate(Float.valueOf(columns[1]))
                .available(Integer.valueOf(columns[2]))
                .build();
       return lender;
    }

}
