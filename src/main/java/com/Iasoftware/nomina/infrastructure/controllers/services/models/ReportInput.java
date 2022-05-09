package com.Iasoftware.nomina.infrastructure.controllers.services.models;
import java.time.LocalDateTime;

public class ReportInput {
    private  String idTechnical;
    private  String idService;
    private LocalDateTime startDate;
    private  LocalDateTime endDate;

    public ReportInput(String id_technical, String id_service, LocalDateTime startDate, LocalDateTime endDate) {
        this.idTechnical = id_technical;
        this.idService = id_service;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getIdTechnical() {
        return idTechnical;
    }

    public void setIdTechnical(String idTechnical) {
        this.idTechnical = idTechnical;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
