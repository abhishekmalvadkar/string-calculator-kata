package com.amalvadkar.sck;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    @Test
    void should_return_zero_if_input_is_empty_string() {
        StringCalculator stringCalculator = new StringCalculator();

        String result = stringCalculator.add("");

        assertThat(result).isEqualTo("0");
    }
}
