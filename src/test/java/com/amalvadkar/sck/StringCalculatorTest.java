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

    @Test
    void should_return_number_as_it_is_if_input_is_single_number() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.add("1")).isEqualTo("1");
        assertThat(stringCalculator.add("12")).isEqualTo("12");
        assertThat(stringCalculator.add("123")).isEqualTo("123");
    }
}
