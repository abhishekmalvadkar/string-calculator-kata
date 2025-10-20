package com.amalvadkar.sck;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
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
    private static final String NUMBER_EXPECTED_BUT_EOF_FOUND_MSG = "Number expected but EOF found.";
    private static final String NEGATIVE_NOT_ALLOWED_MSG = "Negative not allowed : %s";

    public String add(String numbers) {
        if (numbers.isEmpty()) return ZERO;
        if (endsWithAllowedSeparator(numbers)) return NUMBER_EXPECTED_BUT_EOF_FOUND_MSG;
        if (hasSingleNumberWithoutSeparator(numbers)) return numbers;
        if (hasSingleOrManyNegativeNumber(numbers)) return negativeNumbersNotAllowedMsg(numbers);

        return sum(split(numbers));
    }

    private static String negativeNumbersNotAllowedMsg(String numbers) {
        String[] splitNumbersWithNegativeNumbers = split(numbers);
        String negativeNumbers = Stream.of(splitNumbersWithNegativeNumbers)
                .map(Integer::parseInt)
                .filter(integer -> integer < 0)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return NEGATIVE_NOT_ALLOWED_MSG.formatted(negativeNumbers);
    }

    private static boolean hasSingleOrManyNegativeNumber(String numbers) {
        return numbers.contains("-");
    }

    private static boolean endsWithAllowedSeparator(String numbers) {
        return numbers.endsWith(COMMA) || numbers.endsWith(NEW_LINE);
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
