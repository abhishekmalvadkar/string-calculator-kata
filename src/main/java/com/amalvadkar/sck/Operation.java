package com.amalvadkar.sck;

import java.util.function.Function;

public enum Operation {
    ADD(Addition::perform),
    MULTIPLY(Multiplication::perform);

    private final Function<String, String> operation;

    Operation(Function<String, String> operation) {
        this.operation = operation;
    }

    public String performOn(String numbers){
        return operation.apply(numbers);
    }
}
