package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentMethod {
    CASH, CREDIT, DEBIT;

    @JsonCreator
    public static PaymentMethod forValue(String value) {
        return valueOf(value);
    }
}