package com.Iasoftware.nomina.core.domain;

import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;

public class Date {


    public static LocalDateTime start(LocalDateTime startDate) {
         Validate.notNull(startDate,  "Service date start can not be null.");
         Validate.isTrue(LocalDateTime.now().isAfter(startDate), "The date must not be in the future");
         return startDate;
    }

    public static LocalDateTime end(LocalDateTime startDate, LocalDateTime endDate) {
        Validate.notNull(endDate,  "Service date start can not be null.");
        Validate.isTrue(endDate.isAfter(startDate) && endDate.isBefore(LocalDateTime.now()), "it cannot represent a date before the start date, nor can it represent a date and time in the future.");
        return endDate;
    }
}
