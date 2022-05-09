package com.Iasoftware.nomina.core.services;

import com.Iasoftware.nomina.core.domain.Report;
import com.Iasoftware.nomina.shared.domain.WeekDays;

import java.time.LocalDateTime;

public class CalculateNormalHours {
    static Integer normalSentFromSunday = 0;
    private static Integer normalStartHours = 7;
    private static Integer normalEndHours = 20;
    private static Integer maximumNormalHours = normalEndHours - normalStartHours;


    public CalculateNormalHours() {
    }

    public static Integer callNormalHours(Report report, LocalDateTime startWeek) {
        int normalHours = normalSentFromSunday;
        normalSentFromSunday = 0;
            if(!WeekDays.isSunday(report.getStartDate())) {
                if (isMondaySaturday(report.getStartDate(), report.getEndDate(), startWeek)) {
                    if (report.getStartDate().getHour() < normalStartHours && report.getEndDate().getHour() > normalStartHours && report.getEndDate().getHour() <= normalEndHours) {
                        normalHours += report.getEndDate().getHour() - normalStartHours;
                    } else normalHours += validateNormalHours(report.getStartDate(), report.getEndDate());
                }
            }
        return normalHours;
    }


    private static  Boolean isMondaySaturday(LocalDateTime dateStart, LocalDateTime dateEnd, LocalDateTime monday) {
        boolean isMondaySaturday = false;
        int saturday = 5;
        LocalDateTime endWeek = monday.plusDays(saturday);
        if (dateEnd.toLocalDate().isBefore(endWeek.toLocalDate()) || dateEnd.toLocalDate().equals(endWeek.toLocalDate())) {
            if (dateStart.toLocalDate().equals(dateEnd.toLocalDate())) isMondaySaturday = true;
        }
        return isMondaySaturday;
    }


    private static Integer validateNormalHours(LocalDateTime start, LocalDateTime end) {
        int normalHours = 0;
        if (start.getHour() >= normalStartHours && end.getHour() <= normalEndHours) {
            normalHours += end.getHour() - start.getHour();
        } else if (end.getHour() >= normalEndHours && start.getHour() < normalEndHours && start.getHour() >= normalStartHours) {
            normalHours += normalEndHours - start.getHour();
        } else if (start.getHour() <  normalStartHours && end.getHour() > normalEndHours) {
            normalHours += maximumNormalHours;
        }
        return normalHours;
    }


}
