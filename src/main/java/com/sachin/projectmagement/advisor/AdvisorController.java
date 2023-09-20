package com.sachin.projectmagement.advisor;

import com.sachin.projectmagement.exception.NotFoundException;
import com.sachin.projectmagement.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdvisorController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> handleClassNotFound(NotFoundException e) {
        return new ResponseEntity<>(
                new StandardResponse(
                        404, e.getMessage(), null
                ),
                HttpStatus.NOT_FOUND);
    }
}
