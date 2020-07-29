package com.examle.gitAnalyzer.web;

import com.examle.gitAnalyzer.exception.FailedToCloneRepository;
import com.examle.gitAnalyzer.exception.RefsDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RepoControllerAdvice {

    @ExceptionHandler(FailedToCloneRepository.class)
    private ResponseEntity<?> failedToClone(FailedToCloneRepository exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RefsDoesNotExistsException.class)
    private ResponseEntity<?> refsDoesNotExist(RefsDoesNotExistsException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
