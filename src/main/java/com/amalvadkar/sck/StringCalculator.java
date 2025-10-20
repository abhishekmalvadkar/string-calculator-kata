package com.amalvadkar.sck;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class StringCalculator {

    public static final String ZERO = "0";
    public static final String COMMA = ",";

    public String add(String numbers) {
        if (numbers.isEmpty()) return ZERO;
        if (hasSingleNumberWithoutComma(numbers)) return numbers;

        return valueOf(sum(numbers.split(COMMA)));
    }

    private static int sum(String[] numbers) {
        return parseInt(numbers[0]) + parseInt(numbers[1]);
    }

    private static boolean hasSingleNumberWithoutComma(String numbers) {
        return !numbers.contains(COMMA);
    }
}
