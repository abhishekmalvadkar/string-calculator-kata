package com.amalvadkar.sck;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    @Test
    void should_return_zero_if_input_is_empty_string() {
        StringCalculator stringCalculator = new StringCalculator();

        String result = stringCalculator.add("");

        assertThat(result).isEqualTo("0");
    }

    @ParameterizedTest
    @CsvSource({
            "1,1",
            "12,12",
            "123,123"
    })
    void should_return_number_as_it_is_if_input_is_single_number(String numbers, String actual) {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.add(numbers)).isEqualTo(actual);
    }
}
