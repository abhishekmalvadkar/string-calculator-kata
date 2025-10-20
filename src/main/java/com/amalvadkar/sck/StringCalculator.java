package com.amalvadkar.sck;

public class StringCalculator {

    public static final String ZERO = "0";
    public static final String COMMA = ",";

    public String add(String numbers) {
        if (numbers.isEmpty()) return ZERO;
        if (hasSingleNumberWithoutComma(numbers)) return numbers;
        return numbers;
    }

    private static boolean hasSingleNumberWithoutComma(String numbers) {
        return !numbers.contains(COMMA);
    }
}
