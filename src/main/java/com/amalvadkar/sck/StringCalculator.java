package com.amalvadkar.sck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.join;
import static java.lang.String.valueOf;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.joining;

public class StringCalculator {

    private static final List<Character> VALID_CHARACTERS_IN_NUMBERS = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.','-');
    private static final List<Character> PREDEFINED_SEPARATORS = List.of(',', '\n');
    private static final String ZERO = "0";
    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";
    private static final List<String> PREDEFINED_REGEX_KEYWORDS = List.of("|");
    private static final String CUSTOM_SEPARATOR_INDICATOR = "//";
    private static final String NUMBER_EXPECTED_BUT_EOF_FOUND_MSG = "Number expected but EOF found.";
    private static final String NEGATIVE_NOT_ALLOWED_MSG = "Negative not allowed : %s";
    private static final String MINUS_SYMBOL = "-";
    private static final String COMMA_WITH_SPACE_SUFFIX = ", ";
    private static final String NUMBER_EXPECTED_BUT_NON_NUMBER_FOUND_NSG = "Number expected but '%s' found at position %s.";
    private static final String UNKNOWN_CHARACTER_AT_POSITION_MSG = "'%s' expected but '%s' found at position %s.";
    private static final String EMPTY_STRING = "";

    public String add(String numbers) {
        if (numbers.isEmpty()) return ZERO;
        if (hasSingleNumberWithoutSeparator(numbers)) return numbers;

        String error = validate(numbers);
        if (numbersHaveValidation(error)) {
            return error;
        }

        return sum(numbers);
    }

    private static boolean numbersHaveValidation(String error) {
        return !error.isEmpty();
    }

    private String validate(String numbers) {
        List<String> errors = new ArrayList<>();

        if (endsWithAllowedSeparator(numbers)) {
            numberExpectedButEofFoundMessage(errors);
        }
        if (hasSingleOrManyNegativeNumber(numbers)) {
            negativeNumbersNotAllowedMsg(numbers,errors);
        }
        if (hasCustomSeparator(numbers)) {
            errorMessageIfAnyInvalidInCaseOfCustomSeparator(NumbersWithCustomSeparator.from(numbers), errors);
        } else {
            errorMessageIfAnyInvalidInCaseOfPredefinedSeparators(numbers, errors);
        }

        return exists(errors) ? joinedWithNewLine(errors) : EMPTY_STRING;
    }

    private static String joinedWithNewLine(List<String> errors) {
        return join(NEW_LINE, errors);
    }

    private static boolean exists(List<String> errors) {
        return !errors.isEmpty();
    }

    private static void numberExpectedButEofFoundMessage(List<String> errors) {
        errors.add(NUMBER_EXPECTED_BUT_EOF_FOUND_MSG);
    }

    private static void errorMessageIfAnyInvalidInCaseOfPredefinedSeparators(String numbers, List<String> errors) {
        for (int position = 0; position < numbers.length(); position++) {
            Character currentCharacter = numbers.charAt(position);
            if (isSeparator(currentCharacter)) {
                Character previousCharacter = numbers.charAt(position - 1);
                if (isSeparator(previousCharacter)) {
                    errors.add(String.format(NUMBER_EXPECTED_BUT_NON_NUMBER_FOUND_NSG, currentCharacter, position));
                }
            } else if (isNotFromValidAllowedNumbers(currentCharacter)) {
                errors.add(String.format(UNKNOWN_CHARACTER_AT_POSITION_MSG, predefinedSeparators(), currentCharacter, position));
            }
        }
    }

    private static String predefinedSeparators() {
        return PREDEFINED_SEPARATORS.stream()
                .map(String::valueOf)
                .collect(joining("OR"));
    }

    private static boolean isSeparator(Character currentCharacter) {
        return PREDEFINED_SEPARATORS.contains(currentCharacter);
    }

    private static void errorMessageIfAnyInvalidInCaseOfCustomSeparator(NumbersWithCustomSeparator numbersWithCustomSeparator, List<String> errors) {
        String actualNumbers = numbersWithCustomSeparator.actualNumbers();
        String customSeparator = numbersWithCustomSeparator.customSeparator();
        if (hasSingleCharacter(customSeparator)) {
            for (int position = 0; position < actualNumbers.length(); position++) {
                Character currentCharacter = actualNumbers.charAt(position);
                if (is(customSeparator, currentCharacter)) {
                    Character previousCharacter = actualNumbers.charAt(position - 1);
                    if (is(customSeparator,previousCharacter)) {
                        errors.add(String.format(NUMBER_EXPECTED_BUT_NON_NUMBER_FOUND_NSG, currentCharacter, position));
                    }
                } else if (isNotFromValidAllowedNumbers(currentCharacter)) {
                    errors.add(String.format(UNKNOWN_CHARACTER_AT_POSITION_MSG, customSeparator, currentCharacter, position));
                }
            }
        }
    }

    private static boolean hasSingleCharacter(String customSeparator) {
        return customSeparator.length() == 1;
    }

    private static boolean isNotFromValidAllowedNumbers(Character currentCharacter) {
        return !VALID_CHARACTERS_IN_NUMBERS.contains(currentCharacter);
    }

    private static boolean is(String customSeparator, Character currentCharacter) {
        return customSeparator.equals(String.valueOf(currentCharacter));
    }

    private static String sum(String numbers) {
        return valueOf(sum(split(numbers)));
    }

    private static void negativeNumbersNotAllowedMsg(String numbers, List<String> errors) {
        errors.add(NEGATIVE_NOT_ALLOWED_MSG.formatted(extractNegativeNumbers(numbers)));
    }

    private static String extractNegativeNumbers(String numbers) {
        return Stream.of(split(numbers))
                .filter(not(String::isBlank))
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
        NumbersWithCustomSeparator numbersWithCustomSeparator = NumbersWithCustomSeparator.from(numbers);
        return numbersWithCustomSeparator.actualNumbers().split(handlePredefinedRegexKeyword(numbersWithCustomSeparator.customSeparator()));
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
