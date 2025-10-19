package com.amalvadkar.sck;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    @Test
    void should_return_integer_itself_when_passing_single_integer_only_to_calculator() {
        StringCalculator stringCalculator = new StringCalculator();

        assertThat(stringCalculator.execute("1")).isEqualTo(1);
        assertThat(stringCalculator.execute("456")).isEqualTo(456);
        assertThat(stringCalculator.execute("-2")).isEqualTo(-2);
    }
}
