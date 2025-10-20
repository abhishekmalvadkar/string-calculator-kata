package com.amalvadkar.sck;

public class StringCalculator {

    public static final String ZERO = "0";
    public static final String COMMA = ",";

    public String add(String numbers) {
        if (numbers.isEmpty()) return ZERO;
        if (hasSingleNumberWithoutComma(numbers)) return numbers;

        String[] numbersAsString = numbers.split(COMMA);
        return String.valueOf(Integer.parseInt(numbersAsString[0]) + Integer.parseInt(numbersAsString[1]));
    }

    private static boolean hasSingleNumberWithoutComma(String numbers) {
        return !numbers.contains(COMMA);
    }
}
