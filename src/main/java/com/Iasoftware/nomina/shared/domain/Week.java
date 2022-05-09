package com.Iasoftware.nomina.shared.domain;

import com.Iasoftware.nomina.core.domain.TechnicalIdentification;
import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;

public class Week {
    private final TechnicalIdentification idTechnical;
    private  WeekNumber weekNumber;
    private LocalDateTime startWeek;

    public Week(TechnicalIdentification idTechnical, WeekNumber weekNumber) {
        this.idTechnical = Validate.notNull(idTechnical);
        this.weekNumber = Validate.notNull(weekNumber);
    }

    public Week(TechnicalIdentification idTechnical,  LocalDateTime startWeek) {
        this.idTechnical = idTechnical;
        this.startWeek = startWeek;
    }

    public TechnicalIdentification getIdTechnical() {
        return idTechnical;
    }

    public WeekNumber getWeekNumber() {
        return weekNumber;
    }

    public LocalDateTime getStartWeek() {
        return startWeek;
    }

}
