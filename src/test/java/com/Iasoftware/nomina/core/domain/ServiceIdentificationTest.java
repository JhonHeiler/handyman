package com.Iasoftware.nomina.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceIdentificationTest {
    @Test
    @DisplayName("A valid Id should not throw an error")
    void valid_id_service_test() {
        String validId = "1111188888";
        assertDoesNotThrow(() -> {
            new ServiceIdentification(validId);
        });
    }

    @Test
    @DisplayName("Null serviceIdentification should throw an error")
    void null_id_service_tests() {
        String unsafeId = null;
        assertThrows(NullPointerException.class, () -> {
            new ServiceIdentification(unsafeId);
        });
    }


    @Test
    @DisplayName("Invalid serviceIdentification should throw an error")
    void invalid_id_service_id_tests() {
        String unsafeId = "";
        assertThrows(IllegalArgumentException.class, () -> {
            new TechnicalIdentification(unsafeId);
        });
    }



}