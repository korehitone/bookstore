package com.gr_tiga.bookstore.helper.exception;

public class DataAlreadyExistsException extends RuntimeException {

    private final String resourceName;
    private final String detail;

    public DataAlreadyExistsException(String resourceName, String detail) {
        super(resourceName + " " + detail);
        this.resourceName = resourceName;
        this.detail = detail;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getDetail() {
        return detail;
    }

}
