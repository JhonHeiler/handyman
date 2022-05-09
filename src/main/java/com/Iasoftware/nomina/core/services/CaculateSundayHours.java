package com.Iasoftware.nomina.core.services;

import com.Iasoftware.nomina.core.domain.Report;
import com.Iasoftware.nomina.shared.domain.WeekDays;

import java.time.LocalDateTime;

public class CaculateSundayHours {
    static Integer dominical = 0;


    public static Integer callSundayHours(Report report, LocalDateTime startWeek) {
        int sundayHours = dominical;
        dominical = 0;
        if (isMondaySunday(report.getStartDate(), report.getEndDate(), startWeek)) {
            if ((WeekDays.isSunday(report.getStartDate()) && (WeekDays.isSunday(report.getEndDate())))) {
                sundayHours += report.getEndDate().getHour() - report.getStartDate().getHour();
            }
        } else sundayHours += sundayMonday(report.getStartDate(), report.getEndDate(), startWeek);
        return sundayHours;
    }


    private static Boolean isMondaySunday(LocalDateTime dateStart, LocalDateTime dateEnd, LocalDateTime startWeek) {
        boolean isMondaySunday = false;
        int sunday = 6;
        LocalDateTime endWeek = startWeek.plusDays(sunday);
        if (dateEnd.toLocalDate().isBefore(endWeek.toLocalDate()) || dateEnd.toLocalDate().equals(endWeek.toLocalDate())) {
            if (dateStart.toLocalDate().equals(dateEnd.toLocalDate())) isMondaySunday = true;
        }
        return isMondaySunday;
    }


    private static int getNightHoursDifferentDay(LocalDateTime dateStart, LocalDateTime dateEnd, int nightHoursDifferentDay, int maximumHours) {
        if ((dateEnd.getHour() <= 23) && !(dateEnd.getHour() == 0)) {
            nightHoursDifferentDay += dateEnd.getHour() - dateStart.getHour();
        } else if (dateEnd.getHour() == 0) {
            nightHoursDifferentDay += maximumHours - dateStart.getHour();
        }
        return nightHoursDifferentDay;
    }



    private static void saturdaySunday(LocalDateTime dateStart, LocalDateTime dateEnd, int normalStartHours, int endnight, int maximumNormalHours, int maximumHours, int normalEndHours) {
        if (WeekDays.isSaturday(dateStart)) {
            CaculateSundayHours.dominical += dateEnd.getHour();
        }if (dateStart.getHour() >= normalStartHours && dateStart.getHour() <= normalEndHours && WeekDays.isSaturday(dateStart)) {
            CalculateNightHours.endnight += endnight;
            CalculateNormalHours.normalSentFromSunday += normalEndHours - dateStart.getHour();
        } else if (dateStart.getHour() > normalEndHours && WeekDays.isSaturday(dateStart)) {
            CalculateNightHours.endnight += maximumHours - dateStart.getHour();
        }if (dateStart.getHour() <= normalStartHours && WeekDays.isSaturday(dateStart)) {
            CalculateNormalHours.normalSentFromSunday += maximumNormalHours;
            CalculateNightHours.starnight += (normalStartHours - dateStart.getHour());
            CalculateNightHours.endnight += endnight;
        }
    }


    private static Integer sundayMonday(LocalDateTime dateStart, LocalDateTime dateEnd, LocalDateTime startWeek) {
        int nightHoursDifferentDay = 0;
        int sunday = 6;
        int monday = 7;
        int normalStartHours = 7;
        int endnight = 4;
        int maximumNormalHours = 13;
        int maximumHours = 24;
        int normalEndHours = 20;
        LocalDateTime endSunday = startWeek.plusDays(sunday);
        LocalDateTime endWeek = startWeek.plusDays(monday);
        if (dateStart.toLocalDate().isBefore(dateEnd.toLocalDate()) && endWeek.toLocalDate().equals(dateEnd.toLocalDate())) {
            nightHoursDifferentDay = getNightHoursDifferentDay(dateStart, dateEnd, nightHoursDifferentDay, maximumHours);
        }if (dateStart.toLocalDate().isBefore(dateEnd.toLocalDate()) && endSunday.toLocalDate().equals(dateEnd.toLocalDate())) {
            saturdaySunday(dateStart, dateEnd, normalStartHours, endnight, maximumNormalHours, maximumHours, normalEndHours);
        }
        return nightHoursDifferentDay;
    }


}
