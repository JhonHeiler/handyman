package com.Iasoftware.nomina.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReportIdTest {
    @Test
    @DisplayName("A valid Id should not throw an error")
    void valid_id_test() {
        String validId = UUID.randomUUID().toString();
        assertDoesNotThrow(() -> {
            new ReportId(validId);
        });
    }

    @Test
    @DisplayName("Null reportId should throw an error")
    void null_id_service_tests() {
        String unsafeId = null;
        assertThrows(NullPointerException.class, () -> {
            new ReportId(unsafeId);
        });
    }


    @Test
    @DisplayName("Invalid reportId should throw an error")
    void invalid_id_tests() {
        String unsafeId = "";
        assertThrows(IllegalArgumentException.class, () -> {
            new ReportId(unsafeId);
        });
    }




}