package com.Iasoftware.nomina.core.domain;

import org.apache.commons.lang3.Validate;

public class ReportId {
    private final String value;
    public ReportId(String value) {
        Validate.notNull(value,  "Service identification can not be null.");
        Validate.isTrue(value.trim().length() == 36, "report identification must be equal to 36 characters");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ServiceIdentification{" +
                "value='" + value + '\'' +
                '}';
    }
}
