package com.topriddy.cronparser.domain;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CronUnitTest {

    @Test
    public void shouldImplementToString() {
        CronUnit<Integer> minutesCronUnit = new CronUnit<>(asList(5, 10, 15, 20));

        assertThat(minutesCronUnit.toString()).isEqualTo("5 10 15 20");

    }
}