package com.gr_tiga.bookstore.helper.exception;

public class DataNotFoundException extends RuntimeException {

    private final String resourceName;
    private final String idValue;

    public DataNotFoundException(String resourceName, String idValue) {
        super(resourceName + " with id " + idValue + " not found");
        this.resourceName = resourceName;
        this.idValue = idValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getIdValue() {
        return idValue;
    }
}
