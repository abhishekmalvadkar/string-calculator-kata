package com.amalvadkar.sck;

import java.math.BigDecimal;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class StringCalculator {

    public static final String ZERO = "0";
    public static final String COMMA = ",";

    public String add(String numbers) {
        if (numbers.isEmpty()) return ZERO;
        if (hasSingleNumberWithoutComma(numbers)) return numbers;

        return sum(numbers.split(COMMA));
    }

    private static String sum(String[] numbers) {
        if (hasDecimalNumber(numbers)) {
            return valueOf(decimalSum(numbers));
        }
        return valueOf(intSum(numbers));
    }

    private static int intSum(String[] numbers) {
        return parseInt(numbers[0]) + parseInt(numbers[1]);
    }

    private static BigDecimal decimalSum(String[] numbers) {
        return new BigDecimal(numbers[0]).add(new BigDecimal(numbers[1]));
    }

    private static boolean hasDecimalNumber(String[] numbers) {
        return String.join(COMMA, numbers).contains(".");
    }

    private static boolean hasSingleNumberWithoutComma(String numbers) {
        return !numbers.contains(COMMA);
    }
}
