package com.amalvadkar.sck;

import java.util.List;

record NumbersWithCustomSeparator(String actualNumbers, String customSeparator) {
    public static NumbersWithCustomSeparator from(String numbers) {
        List<String> customSeparatorWithNumbers = numbers.lines()
                .toList();
        String customSeparator = customSeparatorWithNumbers.getFirst().substring(2);
        String actualNumbers = customSeparatorWithNumbers.getLast();
        return new NumbersWithCustomSeparator(actualNumbers, customSeparator);
    }
}
