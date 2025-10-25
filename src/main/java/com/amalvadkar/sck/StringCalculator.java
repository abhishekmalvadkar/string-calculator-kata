package com.amalvadkar.sck;

import static com.amalvadkar.sck.Utility.isEmptyOrNull;
import static com.amalvadkar.sck.Validator.validate;

public class StringCalculator {

    public String add(String numbers) {
        return process(numbers, Operation.ADD);
    }

    public String multiply(String numbers) {
        return process(numbers, Operation.MULTIPLY);
    }

    private static String process(String numbers, Operation operation) {
        if (isEmptyOrNull(numbers)) return Constants.ZERO;
        if (hasSingleNumberWithoutSeparator(numbers)) return numbers;

        String error = validate(numbers);
        if (numbersHaveValidation(error)) return error;

        return operation.performOn(numbers);
    }

    private static boolean numbersHaveValidation(String error) {
        return !error.isEmpty();
    }

    private static boolean hasSingleNumberWithoutSeparator(String numbers) {
        return !numbers.contains(Constants.COMMA) && !numbers.contains(Constants.NEW_LINE);
    }
}

