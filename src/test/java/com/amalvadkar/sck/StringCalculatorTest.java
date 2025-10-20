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

    @Test
    void should_return_sum_if_input_is_two_numbers_separated_by_comma() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.add("1,2")).isEqualTo("3");
        assertThat(stringCalculator.add("12,2")).isEqualTo("14");
        assertThat(stringCalculator.add("1,22")).isEqualTo("23");
        assertThat(stringCalculator.add("10,20")).isEqualTo("30");
    }

    @Test
    void should_return_sum_if_input_is_two_numbers_and_it_involved_decimal_number_separated_by_comma() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.add("1.1,2.2")).isEqualTo("3.3");
        assertThat(stringCalculator.add("2.1,2")).isEqualTo("4.1");
        assertThat(stringCalculator.add("2,3.2")).isEqualTo("5.2");
    }

    @Test
    void should_return_sum_if_input_is_multiple_int_numbers_separated_by_comma() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.add("1,2,3")).isEqualTo("6");
        assertThat(stringCalculator.add("1,2,3,4")).isEqualTo("10");
        assertThat(stringCalculator.add("10,20,30,40,50")).isEqualTo("150");
    }

    @Test
    void should_return_sum_if_input_is_multiple_decimal_numbers_separated_by_comma() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.add("1.1,2.2,3.3")).isEqualTo("6.6");
    }

    @Test
    void should_allow_new_line_as_separator_as_well() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.add("1\n2,3")).isEqualTo("6");
        assertThat(stringCalculator.add("1\n2\n3\n4")).isEqualTo("10");
    }
}
