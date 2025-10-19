package com.amalvadkar.sck;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    @Test
    void should_return_integer_itself_when_passing_single_integer_only_to_calculator() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.execute("1")).isEqualTo(1);
        assertThat(stringCalculator.execute("456")).isEqualTo(456);
        assertThat(stringCalculator.execute("-2")).isEqualTo(-2);
    }

    @Test
    void should_return_sum_if_two_numbers_are_passed_as_input_separated_by_plus_operator() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.execute("1+1")).isEqualTo(2);
        assertThat(stringCalculator.execute("57+100")).isEqualTo(157);
        assertThat(stringCalculator.execute("1000+0")).isEqualTo(1000);
    }
}
