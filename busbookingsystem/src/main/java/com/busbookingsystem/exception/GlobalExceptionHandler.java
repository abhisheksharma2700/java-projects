package com.busbookingsystem.exception;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e){
        ErrorResponse errorResponse= new ErrorResponse(e.getMessage(),"please carefully read the message and follow the instructions");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String ,String >> validationExceptionHandler(MethodArgumentNotValidException e) {
        Map<String ,String > error=new HashMap<>();
        String errorMessage = ( "Validation exception occurs due to your invalid input errors");
        error.put(e.getMessage(),errorMessage);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,String>> validGenderHandler(HttpMessageNotReadableException e){
        Map<String,String> error= new HashMap<>();
        String errorMessage=("please enter a valid gender");
         error.put(e.getMessage(),errorMessage);
         return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }


}
