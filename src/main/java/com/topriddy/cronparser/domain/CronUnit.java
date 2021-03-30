package com.topriddy.cronparser.domain;

import java.util.List;
import java.util.stream.Collectors;

public class CronUnit<T> {
    private final List<T> units;

    public CronUnit(List<T> units) {
        this.units = units;
    }

    public List<T> getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return units.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }
}
