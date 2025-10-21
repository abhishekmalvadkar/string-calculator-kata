package com.amalvadkar.sck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.amalvadkar.sck.Utility.hasCustomSeparator;
import static java.lang.String.join;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.joining;

public class Validator {
    public static String validate(String numbers) {
        List<String> errors = new ArrayList<>();

        updateErrorsWithMessageIfNumbersEndsWithAllowedSeparator(numbers, errors);
        updateErrorsWithMessageIfNumbersHaveSingleOrManyNegativeNumber(numbers, errors);
        updateErrorsWithMessageIfPositionIsWrongBasedOnSeparatorType(numbers, errors);

        return exists(errors) ? joinedWithNewLine(errors) : Constants.EMPTY_STRING;
    }

    private static void updateErrorsWithMessageIfNumbersHaveSingleOrManyNegativeNumber(String numbers, List<String> errors) {
        if (hasSingleOrManyNegativeNumber(numbers)) errors.add(Constants.NEGATIVE_NOT_ALLOWED_MSG.formatted(extractNegativeNumbers(numbers)));
    }

    private static void updateErrorsWithMessageIfNumbersEndsWithAllowedSeparator(String numbers, List<String> errors) {
        if (endsWithAllowedSeparator(numbers)) errors.add(Constants.NUMBER_EXPECTED_BUT_EOF_FOUND_MSG);
    }

    private static void updateErrorsWithMessageIfPositionIsWrongBasedOnSeparatorType(String numbers, List<String> errors) {
        if (hasCustomSeparator(numbers)) {
            updateErrorsWithMessageIfPositionIsWrongInCaseOfCustomSeparator(NumbersWithCustomSeparator.from(numbers), errors);
        } else {
            updateErrorsWithMessageIfPositionIsWrongInCaseOfPredefinedSeparators(numbers, errors);
        }
    }

    private static String joinedWithNewLine(List<String> errors) {
        return join(Constants.NEW_LINE, errors);
    }

    private static boolean exists(List<String> errors) {
        return !errors.isEmpty();
    }

    private static void updateErrorsWithMessageIfPositionIsWrongInCaseOfPredefinedSeparators(String numbers, List<String> errors) {
        for (int position = 0; position < numbers.length(); position++) {
            Character currentCharacter = numbers.charAt(position);
            if (isSeparator(currentCharacter)) {
                Character previousCharacter = numbers.charAt(position - 1);
                if (isSeparator(previousCharacter)) {
                    errors.add(String.format(Constants.NUMBER_EXPECTED_BUT_NON_NUMBER_FOUND_NSG, currentCharacter, position));
                }
            } else if (isNotFromKnownCharacterInNumbers(currentCharacter)) {
                errors.add(String.format(Constants.UNKNOWN_CHARACTER_AT_POSITION_MSG, predefinedSeparators(), currentCharacter, position));
            }
        }
    }

    private static String predefinedSeparators() {
        return Constants.PREDEFINED_SEPARATORS.stream()
                .map(String::valueOf)
                .collect(joining("OR"));
    }

    private static boolean isSeparator(Character currentCharacter) {
        return Constants.PREDEFINED_SEPARATORS.contains(currentCharacter);
    }

    private static void updateErrorsWithMessageIfPositionIsWrongInCaseOfCustomSeparator(NumbersWithCustomSeparator numbersWithCustomSeparator, List<String> errors) {
        String actualNumbers = numbersWithCustomSeparator.actualNumbers();
        String customSeparator = numbersWithCustomSeparator.customSeparator();
        if (hasSingleCharacter(customSeparator)) {
            for (int position = 0; position < actualNumbers.length(); position++) {
                Character currentCharacter = actualNumbers.charAt(position);
                if (is(customSeparator, currentCharacter)) {
                    Character previousCharacter = actualNumbers.charAt(position - 1);
                    if (is(customSeparator,previousCharacter)) {
                        errors.add(String.format(Constants.NUMBER_EXPECTED_BUT_NON_NUMBER_FOUND_NSG, currentCharacter, position));
                    }
                } else if (isNotFromKnownCharacterInNumbers(currentCharacter)) {
                    errors.add(String.format(Constants.UNKNOWN_CHARACTER_AT_POSITION_MSG, customSeparator, currentCharacter, position));
                }
            }
        }
    }

    private static boolean hasSingleCharacter(String customSeparator) {
        return customSeparator.length() == 1;
    }

    private static boolean isNotFromKnownCharacterInNumbers(Character currentCharacter) {
        return !Constants.KNOWN_CHARACTERS_IN_NUMBERS.contains(currentCharacter);
    }

    private static boolean is(String customSeparator, Character currentCharacter) {
        return customSeparator.equals(String.valueOf(currentCharacter));
    }

    private static String extractNegativeNumbers(String numbers) {
        return Stream.of(Splitter.split(numbers))
                .filter(not(String::isBlank))
                .map(BigDecimal::new)
                .filter(Validator::negativeNumber)
                .map(String::valueOf)
                .collect(joining(Constants.COMMA_WITH_SPACE_SUFFIX));
    }

    private static boolean negativeNumber(BigDecimal bigDecimal) {
        return bigDecimal.compareTo(BigDecimal.ZERO) < 0;
    }

    private static boolean hasSingleOrManyNegativeNumber(String numbers) {
        return numbers.contains(Constants.MINUS_SYMBOL);
    }

    private static boolean endsWithAllowedSeparator(String numbers) {
        return numbers.endsWith(Constants.COMMA) || numbers.endsWith(Constants.NEW_LINE);
    }
}
