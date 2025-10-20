package com.amalvadkar.sck;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.String.join;
import static java.lang.String.valueOf;

public class StringCalculator {

    public static final String ZERO = "0";
    public static final String COMMA = ",";
    public static final String DECIMAL_DOT = ".";
    public static final String LINE_BREAK = "\n";

    public String add(String numbers) {
        if (numbers.isEmpty()) return ZERO;
        if (hasSingleNumberWithoutSeparator(numbers)) return numbers;

        return sum(split(numbers));
    }

    private static String[] split(String numbers) {
        return replaceCustomSeparatorWithComma(numbers).split(COMMA);
    }

    private static String replaceCustomSeparatorWithComma(String numbers) {
        return numbers.replace(LINE_BREAK, COMMA);
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
        return Stream.of(numbers)
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static boolean hasDecimalNumber(String[] numbers) {
        return join(COMMA, numbers).contains(DECIMAL_DOT);
    }

    private static boolean hasSingleNumberWithoutSeparator(String numbers) {
        return !numbers.contains(COMMA) && !numbers.contains(LINE_BREAK);
    }
}
