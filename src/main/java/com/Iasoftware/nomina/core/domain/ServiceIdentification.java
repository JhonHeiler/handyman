package com.Iasoftware.nomina.core.domain;

import org.apache.commons.lang3.Validate;

public class ServiceIdentification {
    private final String value;
    public ServiceIdentification(String value) {
        Validate.notNull(value,  "Service identification can not be null.");
        Validate.isTrue(value.trim().length() == 10, "service identification must be equal to 10 characters");
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
