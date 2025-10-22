package com.amalvadkar.sck;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

import static com.amalvadkar.sck.Splitter.split;
import static com.amalvadkar.sck.Validator.validate;
import static java.lang.String.valueOf;

public class StringCalculator {

    public String add(String numbers) {
        if (Objects.isNull(numbers) || numbers.isEmpty()) return Constants.ZERO;
        if (hasSingleNumberWithoutSeparator(numbers)) return numbers;

        String error = validate(numbers);
        if (numbersHaveValidation(error)) return error;

        return sum(numbers);
    }

    private static boolean numbersHaveValidation(String error) {
        return !error.isEmpty();
    }

    private static String sum(String numbers) {
        return valueOf(sum(split(numbers)));
    }

    private static BigDecimal sum(String[] numbers) {
        return Stream.of(numbers)
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static boolean hasSingleNumberWithoutSeparator(String numbers) {
        return !numbers.contains(Constants.COMMA) && !numbers.contains(Constants.NEW_LINE);
    }

}
