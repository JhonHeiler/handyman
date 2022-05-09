package com.Iasoftware.nomina.core.services;

import com.Iasoftware.nomina.core.domain.Report;
import com.Iasoftware.nomina.shared.domain.WeekDays;

import java.time.LocalDateTime;
import java.util.List;

public class CalculateHours {
    static Integer normalHours = 0;
    static Integer nightHours = 0;
    static Integer sundayHours = 0;

    static Integer normalOvertime = 0;
    static Integer extraNightHours = 0;
    static Integer extraSundayHour = 0;

    static Integer totalNormalHours = 0;
    static Integer totalNightHours = 0;
    private static final Integer overtimeStarts = 48;
    private static Integer flag = 0;



    private static void equalToZero() {
        normalHours = 0;
        nightHours = 0;
        sundayHours = 0;
        normalOvertime = 0;
        extraNightHours = 0;
        extraSundayHour = 0;
        flag = 0;
    }

    public static void callExtraHours(List<Report> reports, LocalDateTime startWeek) {
        equalToZero();
        mondayHours(reports, startWeek);
        totalNormalHours = normalHours;
        totalNightHours = nightHours;
        tuesDayHours(reports, startWeek, totalNormalHours,totalNightHours );
        totalNormalHours = normalHours;
        totalNightHours = nightHours;
        wednesdayHours(reports, startWeek, totalNormalHours,totalNightHours);
        totalNormalHours = normalHours;
        totalNightHours = nightHours;
        extraHours(reports, startWeek);
    }

    private static void extraHours(List<Report> reports, LocalDateTime startWeek) {
        thursdayHours(reports, startWeek, totalNormalHours,totalNightHours);
        totalNormalHours = normalHours;
        totalNightHours = nightHours;
        fridayHours(reports, startWeek, totalNormalHours,totalNightHours);
        totalNormalHours = normalHours;
        totalNightHours = nightHours;
        saturdayHours(reports, startWeek, totalNormalHours,totalNightHours);
        totalNormalHours = normalHours;
        totalNightHours = nightHours;
        sundayHours(reports, startWeek, totalNormalHours,totalNightHours);
    }


    private static Integer totalMoreStartNight(int total, int startTotal, Report report, LocalDateTime startWeek) {
        extraNightHours = 0;
        normalOvertime = 0;
        nightHours += overtimeStarts - startTotal;
        extraNightHours = (flag == 1) ? 0 : extraNightHours;
        extraNightHours += CalculateNightHours.endnight + (total - overtimeStarts) ;
        normalOvertime += CalculateNormalHours.callNormalHours(report, startWeek);
        flag = 1;
        return total;
    }


    private static void totalMoreNormal(int total, int  startTotal, Report report, LocalDateTime startWeek) {
        if (total <= overtimeStarts && flag == 0) {
            normalHours += CalculateNormalHours.callNormalHours(report, startWeek);
            CalculateNightHours.callNightHours(report, startWeek);
            nightHours += CalculateNightHours.starnight + CalculateNightHours.endnight;
        } else {
            total += CalculateNormalHours.callNormalHours(report, startWeek);
            if (total > overtimeStarts) {
                nightHours += CalculateNightHours.starnight;
                normalHours += overtimeStarts -  startTotal;
                normalOvertime += total - overtimeStarts;
                extraNightHours += CalculateNightHours.endnight;
                flag = 1;
            }
        }
    }


    private static void isExtra(LocalDateTime startWeek, Report report) {
        CalculateNightHours.callNightHours(report, startWeek);
        extraNightHours += CalculateNightHours.starnight + CalculateNightHours.endnight;
        normalOvertime += CalculateNormalHours.callNormalHours(report, startWeek);
    }

    private static void mondayHours(List<Report> reports, LocalDateTime startWeek) {
        normalHours = 0;
        nightHours = 0;
        for (Report report : reports) {
            if (WeekDays.isMonday(report.getStartDate()) && WeekDays.isMonday(report.getEndDate())) {
                CalculateNightHours.callNightHours(report, startWeek);
                normalHours += CalculateNormalHours.callNormalHours(report, startWeek) ;
                nightHours += CalculateNightHours.starnight + CalculateNightHours.endnight;
            }
        }

    }


    private static void tuesDayHours(List<Report> reports, LocalDateTime startWeek,int totalNormalHours,int totalNightHours) {
        normalHours = totalNormalHours;
        nightHours = totalNightHours;
        for (Report report : reports) {
            if (WeekDays.isTuesDay(report.getStartDate()) && WeekDays.isTuesDay(report.getEndDate())) {
                CalculateNightHours.callNightHours(report, startWeek);
                normalHours += CalculateNormalHours.callNormalHours(report, startWeek);
                nightHours += CalculateNightHours.starnight + CalculateNightHours.endnight;
            }
        }
    }


    private static void wednesdayHours(List<Report> reports, LocalDateTime startWeek,int totalNormalHours,int totalNightHours) {
        int startTotal = totalNormalHours + totalNightHours;
        int totalMoreStartNight = 0;
        int totalMoreWeekDays  = 0;
        normalHours = totalNormalHours;
        nightHours = totalNightHours;
        for (Report report : reports) {
            if (WeekDays.isWednesday(report.getStartDate()) && WeekDays.isWednesday(report.getEndDate())) {
                CalculateNightHours.callNightHours(report, startWeek);
                totalMoreWeekDays  = startTotal + CalculateNightHours.starnight;
                if (totalMoreWeekDays  > overtimeStarts) totalMoreStartNight = totalMoreStartNight(totalMoreWeekDays  , startTotal, report, startWeek);
                else  totalMoreNormal(totalMoreWeekDays  ,  totalMoreStartNight, report, startWeek);
            }
        }
    }


    private static void thursdayHours(List<Report> reports, LocalDateTime startWeek,int totalNormalHours,int totalNightHours) {
        int startTotal = totalNormalHours + totalNightHours;
        int totalMoreStartNight = 0;
        int totalMoreThursday  = 0;
        normalHours = totalNormalHours;
        nightHours = totalNightHours;
        for (Report report : reports) {
            if (WeekDays.isThursday(report.getStartDate()) && WeekDays.isThursday(report.getEndDate())) {
                if (flag == 1) {
                    isExtra(startWeek, report);
                } else if(startTotal <= overtimeStarts){
                    CalculateNightHours.callNightHours(report, startWeek);
                    totalMoreThursday =  startTotal + CalculateNightHours.starnight;
                    if (totalMoreThursday > overtimeStarts) totalMoreStartNight = totalMoreStartNight(totalMoreThursday, startTotal, report, startWeek);
                    else  totalMoreNormal(totalMoreThursday, totalMoreStartNight, report, startWeek);
                }
            }
        }
    }


    private static void fridayHours(List<Report> reports, LocalDateTime startWeek,int totalNormalHours,int totalNightHours) {
        int startTotal = totalNormalHours + totalNightHours;
        int totalMoreStartNight = 0;
        int totalMoreFriday  = 0;
        normalHours = totalNormalHours;
        nightHours = totalNightHours;
        for (Report report : reports) {
            if (WeekDays.isFriday(report.getStartDate()) && WeekDays.isFriday(report.getEndDate())) {
                if (flag == 1) {
                    isExtra(startWeek, report);
                } else if(startTotal <= overtimeStarts){
                    CalculateNightHours.callNightHours(report, startWeek);
                    totalMoreFriday =  startTotal + CalculateNightHours.starnight;
                    if (totalMoreFriday > overtimeStarts) totalMoreStartNight = totalMoreStartNight(totalMoreFriday, startTotal, report, startWeek);
                    else  totalMoreNormal(totalMoreFriday, totalMoreStartNight, report, startWeek);

                }
            }
        }
    }


    private static void saturdayHours(List<Report> reports, LocalDateTime startWeek,int totalNormalHours,int totalNightHours) {
        int startTotal = totalNormalHours + totalNightHours;
        int totalMoreStartNight = 0;
        int totalMoreSaturday = 0;
        normalHours = totalNormalHours;
        nightHours = totalNightHours;
        for (Report report : reports) {
            if (WeekDays.isSaturday(report.getStartDate()) && WeekDays.isSaturday(report.getEndDate())) {
                if (flag == 1) {
                    isExtra(startWeek, report);
                } else if(startTotal <= overtimeStarts){
                    CalculateNightHours.callNightHours(report, startWeek);
                    totalMoreSaturday =  startTotal + CalculateNightHours.starnight;
                    if (totalMoreSaturday > overtimeStarts) totalMoreStartNight = totalMoreStartNight(totalMoreSaturday, startTotal, report, startWeek);
                    else totalMoreNormal(totalMoreSaturday, totalMoreStartNight, report, startWeek);
                }
            }

        }
    }


    private static void sundayHours(List<Report> reports, LocalDateTime startWeek,int totalNormalHours,int totalNightHours) {
        int startTotal = totalNormalHours + totalNightHours;
        sundayHours = 0;
        int totalMoreSaturday = 0;
        for (Report report : reports) {
            if (WeekDays.isSunday(report.getStartDate()) && WeekDays.isSunday(report.getEndDate())) {
                if (flag == 1) {
                    extraSundayHour += CaculateSundayHours.callSundayHours(report, startWeek);
                } else {
                    totalMoreSaturday = startTotal + CaculateSundayHours.callSundayHours(report, startWeek);
                    if (totalMoreSaturday >= overtimeStarts) {
                        sundayHours = overtimeStarts - startTotal;
                        startTotal = 0;
                        extraSundayHour = totalMoreSaturday - overtimeStarts;
                    }else sundayHours += CaculateSundayHours.callSundayHours(report, startWeek);

                }
            }

        }
    }

}
