package com.Iasoftware.nomina.shared.domain;

import org.apache.commons.lang3.Validate;

public class WeekNumber {
    private final Integer value;

    public WeekNumber(Integer value) {
        Validate.notNull(value, "week number cannot be null.");
        Validate.isTrue(value >= 1, "week number can not be negative");
        Validate.isTrue(value <= 53, "Week number exceeds week of year range");
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
