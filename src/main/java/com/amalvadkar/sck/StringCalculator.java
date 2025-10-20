package com.amalvadkar.sck;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.join;
import static java.lang.String.valueOf;

public class StringCalculator {

    private static final String ZERO = "0";
    private static final String COMMA = ",";
    private static final String DECIMAL_DOT = ".";
    private static final String NEW_LINE = "\n";
    private static final List<String> PREDEFINED_REGEX_KEYWORDS = List.of("|");
    private static final String CUSTOM_SEPARATOR_INDICATOR = "//";

    public String add(String numbers) {
        if (numbers.isEmpty()) return ZERO;
        if (hasSingleNumberWithoutSeparator(numbers)) return numbers;

        return sum(split(numbers));
    }

    private static String[] split(String numbers) {
        if (hasCustomSeparator(numbers)) {
            return splitWithCustomSeparator(numbers);
        }
        return splitWithAllowedSeparators(numbers);
    }

    private static String[] splitWithAllowedSeparators(String numbers) {
        return replaceNewLineSeparatorWithComma(numbers).split(COMMA);
    }

    private static String[] splitWithCustomSeparator(String numbers) {
        List<String> customSeparatorWithNumbers = numbers.lines()
                .toList();
        String customSeparator = extractCustomSeparator(customSeparatorWithNumbers);
        String actualNumbers = extractActualNumbers(customSeparatorWithNumbers);
        return actualNumbers.split(handlePredefinedRegexKeyword(customSeparator));
    }

    private static String extractActualNumbers(List<String> customSeparatorWithNumbers) {
        return customSeparatorWithNumbers.getLast();
    }

    private static String extractCustomSeparator(List<String> customSeparatorWithNumbers) {
        return customSeparatorWithNumbers.getFirst().substring(2);
    }

    private static boolean hasCustomSeparator(String numbers) {
        return numbers.startsWith(CUSTOM_SEPARATOR_INDICATOR);
    }

    private static String handlePredefinedRegexKeyword(String customSeparator) {
        return PREDEFINED_REGEX_KEYWORDS.contains(customSeparator) ?
                "\\" + customSeparator :
                customSeparator;
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
