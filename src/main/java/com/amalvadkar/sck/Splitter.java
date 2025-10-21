package com.amalvadkar.sck;

import static com.amalvadkar.sck.Utility.hasCustomSeparator;

public class Splitter {
    public static String[] split(String numbers) {
        if (hasCustomSeparator(numbers)) {
            return splitWithCustomSeparator(numbers);
        }
        return splitWithAllowedSeparators(numbers);
    }

    private static String[] splitWithAllowedSeparators(String numbers) {
        return replaceNewLineSeparatorWithComma(numbers).split(Constants.COMMA);
    }

    private static String[] splitWithCustomSeparator(String numbers) {
        NumbersWithCustomSeparator numbersWithCustomSeparator = NumbersWithCustomSeparator.from(numbers);
        return numbersWithCustomSeparator.actualNumbers().split(handlePredefinedRegexKeyword(numbersWithCustomSeparator.customSeparator()));
    }

    private static String handlePredefinedRegexKeyword(String customSeparator) {
        return Constants.PREDEFINED_REGEX_KEYWORDS.contains(customSeparator) ?
                "\\" + customSeparator :
                customSeparator;
    }

    private static String replaceNewLineSeparatorWithComma(String numbers) {
        return numbers.replace(Constants.NEW_LINE, Constants.COMMA);
    }
}
