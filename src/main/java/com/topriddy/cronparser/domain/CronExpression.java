package com.topriddy.cronparser.domain;

public class CronExpression {
    private final CronUnit<Integer> minute;
    private final CronUnit<Integer> hour;
    private final CronUnit<Integer> dayOfMonth;
    private final CronUnit<Integer> month;
    private final CronUnit<Integer> dayOfWeek;
    private final CronCommand command;

    public CronExpression(CronUnit<Integer> minute, CronUnit<Integer> hour, CronUnit<Integer> dayOfMonth,
                          CronUnit<Integer> month, CronUnit<Integer> dayOfWeek, CronCommand command) {
        this.minute = minute;
        this.hour = hour;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.command = command;
    }

    public CronUnit getMinute() {
        return minute;
    }

    public CronUnit<Integer> getHour() {
        return hour;
    }

    public CronUnit<Integer> getDayOfMonth() {
        return dayOfMonth;
    }

    public CronUnit<Integer> getMonth() {
        return month;
    }

    public CronUnit<Integer> getDayOfWeek() {
        return dayOfWeek;
    }

    public CronCommand getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "CronExpression{" +
                "minute=" + minute +
                ", hour=" + hour +
                ", dayOfMonth=" + dayOfMonth +
                ", month=" + month +
                ", dayOfWeek=" + dayOfWeek +
                ", command=" + command +
                '}';
    }

    public void printDisplay() {
        System.out.printf("%-14s %s\n", "minute", minute);
        System.out.printf("%-14s %s\n", "hour", hour);
        System.out.printf("%-14s %s\n", "day of month", dayOfMonth);
        System.out.printf("%-14s %s\n", "month", month);
        System.out.printf("%-14s %s\n", "day of week", dayOfWeek);
        System.out.printf("%-14s %s\n", "command", command);
    }
}
