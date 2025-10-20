package com.amalvadkar.sck;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.String.join;
import static java.lang.String.valueOf;

public class StringCalculator {

    public static final String ZERO = "0";
    public static final String COMMA = ",";
    public static final String DOT = ".";

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
        return Stream.of(numbers)
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private static BigDecimal decimalSum(String[] numbers) {
        return new BigDecimal(numbers[0]).add(new BigDecimal(numbers[1]));
    }

    private static boolean hasDecimalNumber(String[] numbers) {
        return join(COMMA, numbers).contains(DOT);
    }

    private static boolean hasSingleNumberWithoutComma(String numbers) {
        return !numbers.contains(COMMA);
    }
}
