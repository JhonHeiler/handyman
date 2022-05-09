package com.Iasoftware.nomina.core.gateways;

import com.Iasoftware.nomina.core.domain.Report;
import com.Iasoftware.nomina.core.domain.TechnicalIdentification;
import com.Iasoftware.nomina.shared.domain.Week;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ReportRepository {
    void create(Report report);
    List<Report> query(Week week);


}
