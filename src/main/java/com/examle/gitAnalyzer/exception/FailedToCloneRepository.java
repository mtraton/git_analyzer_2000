package com.examle.gitAnalyzer.exception;

public class FailedToCloneRepository extends RuntimeException {

    public FailedToCloneRepository(String message) {
        super(message);
    }
}
