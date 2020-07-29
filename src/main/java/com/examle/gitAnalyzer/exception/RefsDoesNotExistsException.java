package com.examle.gitAnalyzer.exception;

public class RefsDoesNotExistsException extends RuntimeException {

    public RefsDoesNotExistsException(String message) {
        super(message);
    }
}
