package com.amalvadkar.sck;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;

public class StringCalculator {

    private static final String ZERO = "0";
    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";
    private static final List<String> PREDEFINED_REGEX_KEYWORDS = List.of("|");
    private static final String CUSTOM_SEPARATOR_INDICATOR = "//";
    private static final String NUMBER_EXPECTED_BUT_EOF_FOUND_MSG = "Number expected but EOF found.";
    private static final String NEGATIVE_NOT_ALLOWED_MSG = "Negative not allowed : %s";
    private static final String MINUS_SYMBOL = "-";
    private static final String COMMA_WITH_SPACE_SUFFIX = ", ";

    public String add(String numbers) {
        if (numbers.isEmpty()) return ZERO;
        if (endsWithAllowedSeparator(numbers)) return NUMBER_EXPECTED_BUT_EOF_FOUND_MSG;
        if (hasSingleNumberWithoutSeparator(numbers)) return numbers;
        if (hasSingleOrManyNegativeNumber(numbers)) return negativeNumbersNotAllowedMsg(numbers);

        String errorMessage = validate(numbers);
        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }

        return sum(numbers);
    }

    private String validate(String numbers) {
        if (hasCustomSeparator(numbers)) {
            NumbersWithCustomSeparator numbersWithCustomSeparator = prepareNumbersWithCustomSeparator(numbers);
            return errorMessageIfAnyInvalidInCaseOfCustomSeparator(numbersWithCustomSeparator);
        }
        return errorMessageIfAnyInvalid(numbers);
    }

    private static String errorMessageIfAnyInvalid(String numbers) {
        for (int i = 0; i < numbers.length(); i++) {
            Character currentCharacter = numbers.charAt(i);
            List<Character> validCharactersInNumbers = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.');
            if (!validCharactersInNumbers.contains(currentCharacter)) {
                if (!validCharactersInNumbers.contains(numbers.charAt(i - 1))) {
                    return String.format("Number expected but '%s' found at position %s.", currentCharacter, i);
                }
            }
        }
        return "";
    }

    private static String errorMessageIfAnyInvalidInCaseOfCustomSeparator(NumbersWithCustomSeparator numbersWithCustomSeparator) {
        for (int i = 0; i < numbersWithCustomSeparator.actualNumbers.length(); i++) {
            Character currentCharacter = numbersWithCustomSeparator.actualNumbers.charAt(i);
            if (numbersWithCustomSeparator.customSeparator.equals(String.valueOf(currentCharacter))) {
                if (numbersWithCustomSeparator.customSeparator.equals(String.valueOf(numbersWithCustomSeparator.actualNumbers.charAt(i-1)))) {
                    return String.format("Number expected but '%s' found at position %s.", currentCharacter, i);
                }
            }
        }
        return "";
    }

    private static String sum(String numbers) {
        return valueOf(sum(split(numbers)));
    }

    private static String negativeNumbersNotAllowedMsg(String numbers) {
        return NEGATIVE_NOT_ALLOWED_MSG.formatted(extractNegativeNumbers(numbers));
    }

    private static String extractNegativeNumbers(String numbers) {
        return Stream.of(split(numbers))
                .map(BigDecimal::new)
                .filter(StringCalculator::negativeNumber)
                .map(String::valueOf)
                .collect(joining(COMMA_WITH_SPACE_SUFFIX));
    }

    private static boolean negativeNumber(BigDecimal bigDecimal) {
        return bigDecimal.compareTo(BigDecimal.ZERO) < 0;
    }

    private static boolean hasSingleOrManyNegativeNumber(String numbers) {
        return numbers.contains(MINUS_SYMBOL);
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
        NumbersWithCustomSeparator numbersWithCustomSeparator = prepareNumbersWithCustomSeparator(numbers);
        return numbersWithCustomSeparator.actualNumbers().split(handlePredefinedRegexKeyword(numbersWithCustomSeparator.customSeparator()));
    }

    private static NumbersWithCustomSeparator prepareNumbersWithCustomSeparator(String numbers) {
        List<String> customSeparatorWithNumbers = numbers.lines()
                .toList();
        String customSeparator = customSeparatorWithNumbers.getFirst().substring(2);
        String actualNumbers = customSeparatorWithNumbers.getLast();
        return new NumbersWithCustomSeparator(actualNumbers, customSeparator);
    }

    private record NumbersWithCustomSeparator(String actualNumbers, String customSeparator) {
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

    private static BigDecimal sum(String[] numbers) {
        return Stream.of(numbers)
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static boolean hasSingleNumberWithoutSeparator(String numbers) {
        return !numbers.contains(COMMA) && !numbers.contains(NEW_LINE);
    }
}
