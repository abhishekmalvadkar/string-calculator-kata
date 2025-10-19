package com.amalvadkar.sck;

public class StringCalculator {
    public int execute(String input) {
        if (input.contains("+")) {
            String[] parts = input.split("\\+");
            return Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]);
        }
        return Integer.parseInt(input);
    }
}
