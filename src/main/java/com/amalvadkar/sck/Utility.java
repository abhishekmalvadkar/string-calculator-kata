package com.amalvadkar.sck;

import static java.util.Objects.isNull;

public class Utility {
    public static boolean hasCustomSeparator(String numbers) {
        return numbers.startsWith(Constants.CUSTOM_SEPARATOR_INDICATOR);
    }

    public static boolean isEmptyOrNull(String numbers) {
        return isNull(numbers) || numbers.isEmpty();
    }
}
