package com.amalvadkar.sck;

public class Utility {
    public static boolean hasCustomSeparator(String numbers) {
        return numbers.startsWith(Constants.CUSTOM_SEPARATOR_INDICATOR);
    }
}
