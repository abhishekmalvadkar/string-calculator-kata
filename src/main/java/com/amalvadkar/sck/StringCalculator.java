package com.amalvadkar.sck;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.String.join;
import static java.lang.String.valueOf;

public class StringCalculator {

    public static final String ZERO = "0";
    public static final String COMMA = ",";
    public static final String DECIMAL_DOT = ".";
    public static final String NEW_LINE = "\n";

    public String add(String numbers) {
        if (numbers.isEmpty()) return ZERO;
        if (hasSingleNumberWithoutSeparator(numbers)) return numbers;

        return sum(split(numbers));
    }

    private static String[] split(String numbers) {
        if (numbers.startsWith("//")) {
            List<String> customSeparatorWithNumbers = numbers.lines()
                    .toList();
            String customSeparator = customSeparatorWithNumbers.getFirst().substring(2);
            String actualNumbers = customSeparatorWithNumbers.getLast();
            return actualNumbers.split(handlePredefinedRegexKeyword(customSeparator));
        }
        return replaceNewLineSeparatorWithComma(numbers).split(COMMA);
    }

    private static String handlePredefinedRegexKeyword(String customSeparator) {
        return customSeparator.equals("|") ? "\\|" : customSeparator;
    }

    private static String replaceNewLineSeparatorWithComma(String numbers) {
        return numbers.replace(NEW_LINE, COMMA);
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
        return !numbers.contains(COMMA) && !numbers.contains(NEW_LINE);
    }
}
