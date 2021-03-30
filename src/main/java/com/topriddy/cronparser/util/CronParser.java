package com.topriddy.cronparser.util;

import com.topriddy.cronparser.domain.CronCommand;
import com.topriddy.cronparser.domain.CronExpression;
import com.topriddy.cronparser.domain.CronUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class CronParser {

    private static final int ARGS_MINUTE_POSITION = 0;
    private static final int ARGS_HOUR_POSITION = 1;
    private static final int ARGS_DAY_OF_MONTH_POSITION = 2;
    private static final int ARGS_MONTH_POSITION = 3;
    private static final int ARGS_DAY_OF_WEEK_POSITION = 4;
    private static final int ARGS_COMMAND_POSITION = 5;

    private static final int EXPRESSION_LENGTH = 6;
    private static final List<Integer> allMinuteUnits = IntStream.rangeClosed(0, 59)
            .mapToObj(i -> i)
            .collect(toList());
    private static final List<Integer> allHoursUnits = IntStream.rangeClosed(0, 23)
            .mapToObj(i -> i)
            .collect(toList());
    private static final List<Integer> allDayOfMonthUnits = IntStream.rangeClosed(1, 31)
            .mapToObj(i -> i)
            .collect(toList());
    private static final List<Integer> allMonthUnits = IntStream.rangeClosed(1, 12)
            .mapToObj(i -> i)
            .collect(toList());

    private static final List<Integer> allDayOfWeekUnits = IntStream.rangeClosed(0, 6)
            .mapToObj(i -> i)
            .collect(toList());

    private static final String SLASH = "/";
    private static final String ASTERISK = "*";
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";

    public CronExpression parse(String[] args) {
        if (args.length != EXPRESSION_LENGTH) {
            throw new IllegalArgumentException();
        }

        return new CronExpression(
                parseExpression(args[ARGS_MINUTE_POSITION], allMinuteUnits),
                parseExpression(args[ARGS_HOUR_POSITION], allHoursUnits),
                parseExpression(args[ARGS_DAY_OF_MONTH_POSITION], allDayOfMonthUnits),
                parseExpression(args[ARGS_MONTH_POSITION], allMonthUnits),
                parseExpression(args[ARGS_DAY_OF_WEEK_POSITION], allDayOfWeekUnits),
                new CronCommand(args[ARGS_COMMAND_POSITION])
        );
    }

    private <T> CronUnit<T> parseExpression(String expression, List<T> allUnits) {
        if (expression.equals(ASTERISK)) {
            return new CronUnit(allUnits);
        } else if (expression.contains(COMMA)) {
            return parseCommaExpression(expression, allUnits);
        } else if (expression.contains(SLASH)) {
            return parseSlashExpression(expression, allUnits);
        } else if (expression.contains(HYPHEN)) {
            return parseHyphenExpression(expression, allUnits);
        }
        return parseSingleValue(expression, allUnits);
    }

    private <T> CronUnit<T> parseCommaExpression(String expression, List<T> allUnits) {
        List<Integer> units = asList(expression.split(COMMA))
                .stream()
                .map(Integer::parseInt)
                .filter(u -> allUnits.contains(u))
                .collect(toList());
        return new CronUnit(units);
    }

    private <T> CronUnit<T> parseSlashExpression(String expression, List<T> allUnits) {
        String[] slashArgs = expression.split(SLASH);
        int start = 0;
        int end = allUnits.size() - 1;
        int step = parseInt(slashArgs[1]);
        if (slashArgs[0].equals(ASTERISK)) {
            // do nothing
        } else if (slashArgs[0].contains(HYPHEN)) {
            String[] hyphenArgs = slashArgs[0].split(HYPHEN);
            start = allUnits.indexOf(parseInt(hyphenArgs[0]));
            end = allUnits.indexOf(parseInt(hyphenArgs[1]));
        } else {
            start = allUnits.indexOf(parseInt(slashArgs[0]));
        }
        List<T> units = new ArrayList<>();
        for (int i = start; i <= end; i += step) {
            units.add(allUnits.get(i));
        }
        return new CronUnit<>(units);
    }

    private <T> CronUnit<T> parseHyphenExpression(String expression, List<T> allUnits) {
        String[] hyphenArgs = expression.split(HYPHEN);
        int start = allUnits.indexOf(parseInt(hyphenArgs[0]));
        int end = allUnits.indexOf(parseInt(hyphenArgs[1]));
        List<T> units = new ArrayList<>();
        for (int i = start; i <= end; i += 1) {
            units.add(allUnits.get(i));
        }
        return new CronUnit<>(units);
    }

    private <T> CronUnit<T> parseSingleValue(String expression, List<T> allUnits) {
        if (!allUnits.contains(parseInt(expression))) {
            throw new IllegalArgumentException();
        }
        return new CronUnit(singletonList(parseInt(expression)));
    }

}
