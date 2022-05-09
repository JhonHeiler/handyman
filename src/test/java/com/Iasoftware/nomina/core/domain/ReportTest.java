package com.Iasoftware.nomina.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
    @Test
    @DisplayName("A valid Id should not throw an error")
    void valid_id_test() {
        String validId = UUID.randomUUID().toString();
        ReportId id = new ReportId(validId);
        TechnicalIdentification id_technical = new TechnicalIdentification("1077475328");
        ServiceIdentification id_service = new ServiceIdentification("9999933333");
        LocalDateTime dateStart = LocalDateTime.parse("2022-03-21T01:34");
        LocalDateTime dateEnd = LocalDateTime.parse("2022-03-21T02:34");
        assertDoesNotThrow(() -> {
            new Report(id,id_technical,id_service,dateStart,dateEnd);
        });
    }
}