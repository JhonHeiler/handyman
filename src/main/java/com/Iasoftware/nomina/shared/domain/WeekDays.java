package com.Iasoftware.nomina.shared.domain;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class WeekDays {

    public static boolean isMonday(final LocalDateTime ld) {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.MONDAY;
    }


    public static boolean isTuesDay(final LocalDateTime ld) {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.TUESDAY;
    }


    public static boolean isWednesday(final LocalDateTime ld) {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.WEDNESDAY;
    }


    public static boolean isThursday(final LocalDateTime ld) {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.THURSDAY;
    }


    public static boolean isFriday(final LocalDateTime ld) {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.FRIDAY;
    }


    public static boolean isSaturday(final LocalDateTime ld) {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SATURDAY;
    }


    public static boolean isSunday(final LocalDateTime ld) {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY;
    }
}
