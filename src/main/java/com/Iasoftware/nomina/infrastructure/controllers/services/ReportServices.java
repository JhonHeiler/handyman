package com.Iasoftware.nomina.infrastructure.controllers.services;

import com.Iasoftware.nomina.core.domain.Report;
import com.Iasoftware.nomina.core.domain.ReportId;
import com.Iasoftware.nomina.core.domain.ServiceIdentification;
import com.Iasoftware.nomina.core.domain.TechnicalIdentification;
import com.Iasoftware.nomina.core.gateways.ReportRepository;
import com.Iasoftware.nomina.core.services.WeekReport;
import com.Iasoftware.nomina.infrastructure.controllers.services.models.ReportDTO;
import com.Iasoftware.nomina.infrastructure.controllers.services.models.ReportInput;
import com.Iasoftware.nomina.shared.domain.Week;
import com.Iasoftware.nomina.shared.domain.WeekNumber;
import com.Iasoftware.nomina.shared.domain.WorkingHours;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class ReportServices {
    private final ReportRepository reportRepository;

    public ReportServices(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }


    public ReportDTO createService(ReportInput reportInput) {
        String value = UUID.randomUUID().toString();
        try {
            Report report = new Report(
                    new ReportId(value),
                    new TechnicalIdentification(reportInput.getIdTechnical()),
                    new ServiceIdentification(reportInput.getIdService()),
                    reportInput.getStartDate(),
                    reportInput.getEndDate()
            );
            reportRepository.create(report);
            return ReportDTO.fromDomain(report);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Error validating service data", e);
        }
    }

    public WorkingHours queryServices(String idTechnical, Integer weekNumber) {
        Week week = new Week(
                new TechnicalIdentification(idTechnical),
                new WeekNumber(weekNumber)
        );

        return WeekReport.weekDate(week);
    }


}
