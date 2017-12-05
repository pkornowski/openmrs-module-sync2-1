package org.openmrs.module.sync2.api.model.enums;

public enum Status {
    SUCCESS_AND_FAILURE(null), SUCCESS(true), FAILURE(false);

    private final Boolean value;

    Status(Boolean value) {
        this.value = value;
    }

    public Boolean isSuccess() {
        return this.value;
    }
}
