package com.Iasoftware.nomina.core.services;

import com.Iasoftware.nomina.core.domain.Report;
import com.Iasoftware.nomina.core.domain.TechnicalIdentification;
import com.Iasoftware.nomina.core.gateways.ReportRepository;
import com.Iasoftware.nomina.shared.domain.Week;
import com.Iasoftware.nomina.shared.domain.WeekNumber;
import com.Iasoftware.nomina.shared.domain.WorkingHours;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.List;

@Service
public class WeekReport {
    private static ReportRepository repository;

    public WeekReport(ReportRepository repository) {
        WeekReport.repository = repository;
    }


    public static WorkingHours weekDate(Week week) {
        String start = WeekNumberToDate(week.getWeekNumber());
        Week weeks = new Week(week.getIdTechnical(), stringToLocalDateTime(start));
        List<Report> report = repository.query(weeks);
        CalculateHours.callExtraHours(report, stringToLocalDateTime(start));
        return addWorkingHours(week.getIdTechnical());
    }


    private static WorkingHours addWorkingHours(TechnicalIdentification idTechnical) {
        WorkingHours workingHours = new WorkingHours();
        workingHours.setIdtechnical(idTechnical.getValue());
        workingHours.setNormalHours(CalculateHours.normalHours);
        workingHours.setNightHours(CalculateHours.nightHours);
        workingHours.setSundayHours(CalculateHours.sundayHours);
        workingHours.setNormalOvertime(CalculateHours.normalOvertime);
        workingHours.setExtraNightHours(CalculateHours.extraNightHours);
        workingHours.setExtraSundayHours(CalculateHours.extraSundayHour);
        return workingHours;

    }


    private static String WeekNumberToDate(WeekNumber weekNumber) {
        LocalDate date = LocalDate.now()
                .with(WeekFields.ISO.weekOfWeekBasedYear(), weekNumber.getValue())
                .with(WeekFields.ISO.dayOfWeek(), DayOfWeek.MONDAY.getValue());
        return  date.toString();
    }


    private static LocalDateTime stringToLocalDateTime(String fecha) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(fecha, dateTimeFormatter).atStartOfDay();
    }



}
