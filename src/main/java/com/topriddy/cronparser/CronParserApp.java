package com.topriddy.cronparser;

import com.topriddy.cronparser.domain.CronExpression;
import com.topriddy.cronparser.util.CronParser;

public class CronParserApp {
    public static void main(String args[]) {
        String[] testArgs = new String[]{"*/15", "0", "1,15", "*", "1-5", "/usr/bin/find"};

        CronParser cronParser = new CronParser();
        CronExpression cronExpression = cronParser.parse(testArgs);

        cronExpression.printDisplay();
    }
}
