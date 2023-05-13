package com.example.carcatalog.web;

import com.example.carcatalog.exception.CarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class CarNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(CarNotFoundException ex) {
        return ex.getMessage();
    }
}
