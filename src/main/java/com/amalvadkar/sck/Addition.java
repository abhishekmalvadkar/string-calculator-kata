package com.amalvadkar.sck;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.amalvadkar.sck.Splitter.split;

public class Addition {
    public static String perform(String numbers) {
        String[] actualNumber = split(numbers);
        BigDecimal result = Stream.of(actualNumber)
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return String.valueOf(result);
    }
}
