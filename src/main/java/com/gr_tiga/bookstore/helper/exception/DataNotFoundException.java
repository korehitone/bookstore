package com.gr_tiga.bookstore.helper.exception;

public class DataNotFoundException extends RuntimeException {

    private final String resourceName;
    private final String uidValue;

    public DataNotFoundException(String resourceName, String uidValue) {
        super(resourceName + " with uid/id " + uidValue + " not found");
        this.resourceName = resourceName;
        this.uidValue = uidValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getIdValue() {
        return uidValue;
    }
}
