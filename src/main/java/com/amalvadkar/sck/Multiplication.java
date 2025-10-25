package com.amalvadkar.sck;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.amalvadkar.sck.Splitter.split;

public class Multiplication {
    public static String perform(String numbers) {
        String[] actualNumbers = split(numbers);
        BigDecimal result = Stream.of(actualNumbers)
                .map(BigDecimal::new)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
        return String.valueOf(result);
    }
}
