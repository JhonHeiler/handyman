package com.Iasoftware.nomina.core.domain;
import org.apache.commons.lang3.Validate;
import java.time.LocalDateTime;
public class Report {
    private final ReportId id;
    private final TechnicalIdentification idTechnical;
    private final ServiceIdentification idService;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Report(ReportId id, TechnicalIdentification idTechnical, ServiceIdentification idService, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.idTechnical = idTechnical;
        this.idService = idService;
        this.startDate = Date.start(startDate);
        this.endDate = Date.end(startDate,endDate);
    }

    public ReportId getId() {
        return id;
    }

    public TechnicalIdentification getIdTechnical() {
        return idTechnical;
    }

    public ServiceIdentification getIdService() {
        return idService;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
