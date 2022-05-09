package com.Iasoftware.nomina.infrastructure.gateways.models;
import com.Iasoftware.nomina.core.domain.Report;
import com.Iasoftware.nomina.core.domain.ReportId;
import com.Iasoftware.nomina.core.domain.ServiceIdentification;
import com.Iasoftware.nomina.core.domain.TechnicalIdentification;

import java.time.LocalDateTime;

public class ReportDBO {
    private String id;
    private String idTechnical;
    private String idService;
    private LocalDateTime startDate;
    private  LocalDateTime endDate;

    public ReportDBO(String id, String idTechnical, String idService, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.idTechnical = idTechnical;
        this.idService = idService;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ReportDBO(){

    }

    public Report toDomain() {
        return new Report(
                new ReportId(id),
                new TechnicalIdentification(idTechnical),
                new ServiceIdentification(idService),
                this.startDate,
                this.endDate
        );
    }

    public static ReportDBO fromDomain(Report report) {
        return new ReportDBO(
                report.getId().toString(),
                report.getIdTechnical().toString(),
                report.getIdService().toString(),
                report.getStartDate(),
                report.getEndDate()
        );
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setIdTechnical(String idTechnical) {
        this.idTechnical = idTechnical;
    }


    public void setIdService(String idService) {
        this.idService = idService;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
