package com.soc.soar.exception;

import com.soc.soar.common.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    ApiResponse apiResponse = new ApiResponse();

    @Value("${debug:false}") // Injects the value of debug property from application.yml
    private boolean debugMode;

    @ExceptionHandler
    public ResponseEntity<Object> handleException(ArithmeticException arithmeticException){

        if (debugMode) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            arithmeticException.printStackTrace(pw);
            String stackTrace = sw.toString();

            apiResponse.setMessage(arithmeticException.getMessage());
            apiResponse.setTimeStamp(LocalDateTime.now());
            apiResponse.setStackTrace(stackTrace);

            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append(". ");
        }
        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> exceptionHandler(Exception exception){

        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);

    }
}
