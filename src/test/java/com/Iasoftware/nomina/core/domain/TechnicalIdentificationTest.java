package com.Iasoftware.nomina.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechnicalIdentificationTest {
    @Test
    @DisplayName("A valid Id should not throw an error")
    void valid_id_technical_test() {
        String validId = "1077475328";
        assertDoesNotThrow(() -> {
            new TechnicalIdentification(validId);
        });
    }

    @Test
    @DisplayName("Null technicalIdentification should throw an error")
    void null_id_technical_tests() {
        String unsafeId = null;
        assertThrows(NullPointerException.class, () -> {
            new TechnicalIdentification(unsafeId);
        });
    }


    @Test
    @DisplayName("Invalid technicalIdentification should throw an error")
    void invalid_id_technical_id_tests() {
        String unsafeId = "";
        assertThrows(IllegalArgumentException.class, () -> {
            new TechnicalIdentification(unsafeId);
        });
    }


}