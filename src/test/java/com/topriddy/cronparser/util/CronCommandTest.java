package com.topriddy.cronparser.util;

import com.topriddy.cronparser.domain.CronCommand;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CronCommandTest {

    @Test
    public void shouldImplementToString() {
        String command = "/usr/bin/find";

        CronCommand cronCommand = new CronCommand(command);

        assertThat(cronCommand.toString()).isEqualTo(command);
    }
}