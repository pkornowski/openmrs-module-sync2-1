package org.openmrs.module.sync2.api.model.enums;

public enum Resources {
    ALL(""), PATIENT("patient"), LOCATION("location");

    private final String name;

    Resources(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
