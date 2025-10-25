package com.amalvadkar.sck;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.amalvadkar.sck.Splitter.split;

public enum Operation {
    ADD(Operation::add),
    MULTIPLY(Operation::multiply);

    private final Function<String, String> operation;

    Operation(Function<String, String> operation) {
        this.operation = operation;
    }

    public String performOn(String numbers){
        return operation.apply(numbers);
    }

    public static String add(String numbers) {
        return String.valueOf(add(split(numbers)));
    }

    public static BigDecimal add(String[] numbers) {
        return Stream.of(numbers)
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static String multiply(String numbers) {
        return String.valueOf(multiply(split(numbers)));
    }

    public static BigDecimal multiply(String[] numbers) {
        return Stream.of(numbers)
                .map(BigDecimal::new)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
    }
}
