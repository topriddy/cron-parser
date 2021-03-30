package com.topriddy.cronparser.domain;

public class CronCommand {
    private final String command;

    public CronCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }
}
