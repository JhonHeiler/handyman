package com.Iasoftware.nomina.core.domain;
import org.apache.commons.lang3.Validate;

public class TechnicalIdentification {
    private final String value;
    public TechnicalIdentification(String value) {
        Validate.notNull(value,  "technical identification can not be null.");
        Validate.isTrue(value.trim().length() == 10, "technical identification must be equal to 10 characters");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return  value;
    }
}
