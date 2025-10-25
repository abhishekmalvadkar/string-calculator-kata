package com.amalvadkar.sck;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorMultiplicationTest {

    @Test
    void should_return_zero_if_input_is_empty_string() {
        StringCalculator stringCalculator = new StringCalculator();

        String result = stringCalculator.multiply("");

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

        assertThat(stringCalculator.multiply(numbers)).isEqualTo(actual);
    }

    @Test
    void should_return_multiplication_if_input_is_two_numbers_separated_by_comma() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("1,2")).isEqualTo("2");
        assertThat(stringCalculator.multiply("12,2")).isEqualTo("24");
        assertThat(stringCalculator.multiply("1,22")).isEqualTo("22");
        assertThat(stringCalculator.multiply("10,20")).isEqualTo("200");
    }

    @Test
    void should_return_multiplication_if_input_is_two_numbers_and_it_involved_decimal_number_separated_by_comma() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("1.1,2.2")).isEqualTo("2.42");
        assertThat(stringCalculator.multiply("2.1,2")).isEqualTo("4.2");
        assertThat(stringCalculator.multiply("2,3.2")).isEqualTo("6.4");
    }

    @Test
    void should_return_multiplication_if_input_is_multiple_int_numbers_separated_by_comma() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("1,2,3")).isEqualTo("6");
        assertThat(stringCalculator.multiply("1,2,3,4")).isEqualTo("24");
        assertThat(stringCalculator.multiply("10,20,30,40,50")).isEqualTo("12000000");
    }

    @Test
    void should_return_multiplication_if_input_is_multiple_decimal_numbers_separated_by_comma() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("1.1,2.2,3.3")).isEqualTo("7.986");
    }

    @Test
    void should_allow_new_line_as_separator_as_well() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("1\n2,3")).isEqualTo("6");
        assertThat(stringCalculator.multiply("1\n2\n3\n4")).isEqualTo("24");
    }

    @Test
    void should_allow_custom_separator() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("//;\n1;2")).isEqualTo("2");
        assertThat(stringCalculator.multiply("//|\n1|2|3")).isEqualTo("6");
        assertThat(stringCalculator.multiply("//sep\n2sep3")).isEqualTo("6");
    }

    @Test
    void should_return_error_msg_if_input_has_allowed_separator_at_the_end() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("1,3,")).isEqualTo("Number expected but EOF found.");
        assertThat(stringCalculator.multiply("1,3\n")).isEqualTo("Number expected but EOF found.");
    }

    @Test
    void should_return_error_message_if_input_has_single_or_multiple_int_negative_numbers() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("-1,2")).isEqualTo("Negative not allowed : -1");
        assertThat(stringCalculator.multiply("2,-4,-5")).isEqualTo("Negative not allowed : -4, -5");

        assertThat(stringCalculator.multiply("-1\n2")).isEqualTo("Negative not allowed : -1");
        assertThat(stringCalculator.multiply("2\n-4\n-5,-3")).isEqualTo("Negative not allowed : -4, -5, -3");

        assertThat(stringCalculator.multiply("//;\n-1;2")).isEqualTo("Negative not allowed : -1");
        assertThat(stringCalculator.multiply("//abc\n2abc-4abc-5abc-3")).isEqualTo("Negative not allowed : -4, -5, -3");
    }

    @Test
    void should_return_error_message_if_input_has_single_or_multiple_decimal_negative_numbers() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("-1.5,2")).isEqualTo("Negative not allowed : -1.5");
        assertThat(stringCalculator.multiply("2,-4.5,-5.5,-9")).isEqualTo("Negative not allowed : -4.5, -5.5, -9");
    }

    @Test
    void should_return_error_message_if_separator_is_giving_at_the_position_of_number() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("175.2,\n35")).isEqualTo("Number expected but '\n' found at position 6.");
        assertThat(stringCalculator.multiply("//;\n175.2;;35")).isEqualTo("Number expected but ';' found at position 6.");
    }

    @Test
    void should_return_error_message_if_any_unknown_character_coming_at_wrong_position() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("//|\n1|2,3")).isEqualTo("'|' expected but ',' found at position 3.");
        assertThat(stringCalculator.multiply("1|2,3")).isEqualTo("',OR\n' expected but '|' found at position 1.");
    }

    @Test
    void should_return_multiple_error_messages_separated_by_new_line_if_many_validation_failed() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("-1,,2")).isEqualTo("Negative not allowed : -1\nNumber expected but ',' found at position 3.");
        assertThat(stringCalculator.multiply("-1,,-2")).isEqualTo("Negative not allowed : -1, -2\nNumber expected but ',' found at position 3.");
    }

    @Test
    void should_return_zero_if_input_is_null() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply(null)).isEqualTo("0");
    }

    @Test
    void should_return_zero_if_input_is_lot_of_whitespaces() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.multiply("                            ")).isEqualTo("0");
    }

}
