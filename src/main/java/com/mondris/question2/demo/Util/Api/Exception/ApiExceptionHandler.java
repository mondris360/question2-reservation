package com.mondris.question2.demo.Util.Api.Exception;


import com.mondris.question2.demo.Util.Api.Exception.CustomErrorClass.CustomException;
import com.mondris.question2.demo.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
import com.mondris.question2.demo.Util.Api.Exception.CustomErrorClass.InvalidInputException;
import com.mondris.question2.demo.Util.Api.Exception.CustomErrorClass.NotFoundException;
import com.mondris.question2.demo.Util.Api.Exception.CustomErrorResponse.ApiExceptionMessage;
import com.mondris.question2.demo.Util.Api.Exception.CustomErrorResponse.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
@ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        HttpStatus httpStatus =  HttpStatus.BAD_REQUEST;
        ApiExceptionMessage apiExceptionMessage =  new ApiExceptionMessage(e.getMessage(), httpStatus, e.getPath());
        logger.warn(e.getMessage());
        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }

    @ExceptionHandler(value ={CustomException.class})
    public ResponseEntity<Object> handleCustomException(CustomException e) {
          HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
          ApiExceptionMessage  apiExceptionMessage = new ApiExceptionMessage(e.getMessage(), httpStatus, e.getPath());
          logger.warn(e.getMessage());
        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }

    @ExceptionHandler(value ={InvalidInputException.class})
    public ResponseEntity<Object> handleInvalidInputExceptionException(InvalidInputException e) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        ApiExceptionMessage  apiExceptionMessage = new ApiExceptionMessage(e.getMessage(), httpStatus, e.getPath());
        logger.warn(e.getMessage());
        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }




    @ExceptionHandler(value ={NotFoundException.class})
    public ResponseEntity<Object> handleUnAuthorizedException(NotFoundException e) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ApiExceptionMessage  apiExceptionMessage = new ApiExceptionMessage(e.getMessage(), httpStatus, e.getPath());
        logger.warn(e.getMessage());
        return new ResponseEntity<>(apiExceptionMessage, httpStatus);
    }






    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, "Invalid User Input", errorList);
        return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);
    }

}

