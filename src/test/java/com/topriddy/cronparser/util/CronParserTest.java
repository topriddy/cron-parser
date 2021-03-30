package com.topriddy.cronparser.util;

import com.topriddy.cronparser.domain.CronExpression;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CronParserTest {

    private final CronParser cronParser = new CronParser();

    @Test
    public void shouldParseCommand() {
        String[] args = new String[]{"*", "*", "*", "*", "*", "/usr/find"};

        CronExpression cronExpression = cronParser.parse(args);

        assertThat(cronExpression.getCommand().toString()).isEqualTo("/usr/find");
    }

    @Test
    public void shouldParseAllMinutes() {
        String[] args = new String[]{"*", "*", "*", "*", "*", "/usr/find"};

        CronExpression cronExpression = cronParser.parse(args);

        assertThat(cronExpression.getMinute().getUnits()).hasSize(60);
        assertThat(cronExpression.getMinute().getUnits()).containsExactly(
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
                30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
                40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
                50, 51, 52, 53, 54, 55, 56, 57, 58, 59);

    }

    @Test
    public void shouldParseAllHours() {
        String[] args = new String[]{"*", "*", "*", "*", "*", "/usr/find"};

        CronExpression cronExpression = cronParser.parse(args);

        assertThat(cronExpression.getHour().getUnits()).hasSize(24);
        assertThat(cronExpression.getHour().getUnits()).containsExactly(
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                20, 21, 22, 23);
    }

    @Test
    public void shouldParseAllDayOfMonths() {
        String[] args = new String[]{"*", "*", "*", "*", "*", "/usr/find"};

        CronExpression cronExpression = cronParser.parse(args);

        assertThat(cronExpression.getDayOfMonth().getUnits()).hasSize(31);
        assertThat(cronExpression.getDayOfMonth().getUnits()).containsExactly(
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
                30, 31);
    }

    @Test
    public void shouldParseAllMonths() {
        String[] args = new String[]{"*", "*", "*", "*", "*", "/usr/find"};

        CronExpression cronExpression = cronParser.parse(args);

        assertThat(cronExpression.getMonth().getUnits()).hasSize(12);
        assertThat(cronExpression.getMonth().getUnits()).containsExactly(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    }

    @Test
    public void shouldParseAllDayOfWeekUnits() {
        String[] args = new String[]{"*", "*", "*", "*", "*", "/usr/find"};

        CronExpression cronExpression = cronParser.parse(args);

        assertThat(cronExpression.getDayOfWeek().getUnits()).hasSize(7);
        assertThat(cronExpression.getDayOfWeek().getUnits()).containsExactly(
                0, 1, 2, 3, 4, 5, 6);
    }

    //Comma
    @Test
    public void shouldParseCommaDayOfMonths() {
        String[] args = new String[]{"*", "*", "1,15", "*", "*", "/usr/find"};

        CronExpression cronExpression = cronParser.parse(args);

        assertThat(cronExpression.getDayOfMonth().getUnits()).hasSize(2);
        assertThat(cronExpression.getDayOfMonth().getUnits()).containsExactly(1, 15);
    }

    //Slash with Basic
    @Test
    public void shouldParseBasicSlashMinutes() {
        String[] args = new String[]{"5/15", "*", "*", "*", "*", "/usr/find"};

        CronExpression cronExpression = cronParser.parse(args);

        assertThat(cronExpression.getMinute().getUnits()).hasSize(4);
        assertThat(cronExpression.getMinute().getUnits()).containsExactly(
                5, 20, 35, 50);
    }

    //Slash with Asterisk
    @Test
    public void shouldParseSlashWithAsteriskMinutes() {
        String[] args = new String[]{"*/15", "*", "*", "*", "*", "/usr/find"};

        CronExpression cronExpression = cronParser.parse(args);

        assertThat(cronExpression.getMinute().getUnits()).hasSize(4);
        assertThat(cronExpression.getMinute().getUnits()).containsExactly(
                0, 15, 30, 45);
    }

    //Slash with Hyphen
    @Test
    public void shouldParseSlashWithHyphenMinutes() {
        String[] args = new String[]{"3-59/15", "*", "*", "*", "*", "/usr/find"};

        CronExpression cronExpression = cronParser.parse(args);

        assertThat(cronExpression.getMinute().getUnits()).hasSize(4);
        assertThat(cronExpression.getMinute().getUnits()).containsExactly(
                3, 18, 33, 48);
    }

    //Basic Hyphen
    @Test
    public void shouldParseBasicHyphenDayOfWeek() {
        String[] args = new String[]{"*", "*", "*", "*", "1-5", "/usr/find"};

        CronExpression cronExpression = cronParser.parse(args);

        assertThat(cronExpression.getDayOfWeek().getUnits()).hasSize(5);
        assertThat(cronExpression.getDayOfWeek().getUnits()).containsExactly(
                1, 2, 3, 4, 5);
    }

    //Basic Single Value
    @Test
    public void shouldParseBasicValueHour() {
        String[] args = new String[]{"*", "0", "*", "*", "*", "/usr/find"};

        CronExpression cronExpression = cronParser.parse(args);

        assertThat(cronExpression.getHour().getUnits()).hasSize(1);
        assertThat(cronExpression.getHour().getUnits()).containsExactly(0);
    }
}