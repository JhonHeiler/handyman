package com.Iasoftware.nomina.core.services;

import com.Iasoftware.nomina.core.domain.Report;
import com.Iasoftware.nomina.shared.domain.WeekDays;

import java.time.LocalDateTime;

public class CalculateNightHours {
    static Integer starnight = 0;
    static Integer endnight = 0;

    private static Integer normalStartHours = 7;
    private static Integer normalEndHours = 20;
    private static Integer  maximumHours = 24;

    static Integer quantityEndnight = 4;

    public static void callNightHours(Report report, LocalDateTime startWeek) {
        starnight = 0;
        endnight = 0;
        if (!WeekDays.isSunday(report.getStartDate())) {
            nightHoursDifferentDay(report.getStartDate(), report.getEndDate());
            if (isMondaySaturday(report.getStartDate(), report.getEndDate(), startWeek)) {
                if (report.getEndDate().getHour() >= normalEndHours && report.getStartDate().getHour() >= normalStartHours && report.getStartDate().getHour() <= normalEndHours) {
                    endnight += report.getEndDate().getHour() - normalEndHours;
                } else {
                    validateNightHours(report.getStartDate(), report.getEndDate());
                }
            }
        }
    }


    private static Boolean isMondaySaturday(LocalDateTime dateStart, LocalDateTime dateEnd, LocalDateTime startWeek) {
        boolean isMondaySaturday = false;
        int saturday = 6;
        LocalDateTime endWeek = startWeek.plusDays(saturday);
        if (dateEnd.toLocalDate().isBefore(endWeek.toLocalDate()) || dateEnd.toLocalDate().equals(endWeek.toLocalDate())) {
            if (dateStart.toLocalDate().equals(dateEnd.toLocalDate())) {
                isMondaySaturday = true;
            }
        }
        return isMondaySaturday;
    }


    private static void nightHoursDifferentDay(LocalDateTime dateStart, LocalDateTime dateEnd) {

        if (!(dateStart.toLocalDate().equals(dateEnd.toLocalDate()))) {
            if (!WeekDays.isSunday(dateEnd) && !WeekDays.isSunday(dateStart)) {
                sundayDifferent(dateStart, dateEnd);
            }
        }
    }


    private static void validateNightHours(LocalDateTime dateStart, LocalDateTime dateEnd) {
        if (!WeekDays.isSunday(dateStart)) {
            if (dateEnd.getHour() < normalStartHours) starnight += dateEnd.getHour() - dateStart.getHour();
            else if (dateEnd.getHour() >= normalStartHours && dateEnd.getHour() < normalEndHours && dateStart.getHour() < normalStartHours && !WeekDays.isSunday(dateEnd))
                starnight += normalStartHours - dateStart.getHour();
            else if (dateEnd.getHour() <= 23 && dateStart.getHour() >= normalEndHours) endnight += dateEnd.getHour() - dateStart.getHour();
            else if (dateEnd.getHour() >= normalEndHours && dateStart.getHour() >= normalStartHours) endnight += dateEnd.getHour() - normalEndHours;
            else if (dateEnd.getHour() >= normalEndHours && dateStart.getHour() < normalStartHours) {
                starnight += (normalStartHours - dateStart.getHour());
                endnight += dateEnd.getHour() - normalEndHours;
            }
        }
    }


    private static void sundayDifferent(LocalDateTime dateStart, LocalDateTime dateEnd) {
        if (dateStart.getHour() >= normalEndHours && dateEnd.getHour() <= normalStartHours) {
            endnight += maximumHours - dateStart.getHour();
            starnight += dateEnd.getHour();
        } else if (dateStart.getHour() <= normalEndHours && dateEnd.getHour() >= normalStartHours && dateEnd.getHour() <= normalEndHours) {
            endnight += quantityEndnight;
            starnight += normalStartHours;
        } else if ((dateStart.getHour() >= normalStartHours) && dateStart.getHour() <= normalEndHours && dateEnd.getHour() <= normalStartHours) {
            endnight += quantityEndnight;
            starnight += dateEnd.getHour();
        } else if ((dateStart.getHour() >= normalStartHours) && dateStart.getHour() <= normalEndHours && dateEnd.getHour() <= 23) {
            starnight += normalStartHours;
            endnight += quantityEndnight + (dateEnd.getHour() - normalEndHours);
        } else if (dateEnd.getHour() == 0 && dateStart.getHour() >= normalEndHours) {
            endnight += maximumHours - dateStart.getHour();
        }else if (dateEnd.getHour() == 0 && dateStart.getHour() <= normalStartHours) {
            starnight += (normalStartHours - dateStart.getHour());
            endnight += quantityEndnight;
        }
    }



}
