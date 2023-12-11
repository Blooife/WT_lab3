package com.es.core.entity.book;

public class BookNotFoundException extends RuntimeException {
    public String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BookNotFoundException() {
        this.errorMessage = "Phone not found!";
    }
}
