package com.rosendo.transferSystem.exceptions.handlers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rosendo.transferSystem.exceptions.ExceptionResponse;
import com.rosendo.transferSystem.exceptions.ResourceNotFoundException;
import com.rosendo.transferSystem.exceptions.TransactionDeniedException;
import com.rosendo.transferSystem.exceptions.UserExistsException;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler{

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleNotFoundException(Exception exception, WebRequest webRequest){

    ExceptionResponse exceptionsResponse = new ExceptionResponse(
      exception.getMessage(),
      new Date(),
      webRequest.getDescription(false)
    );
    return new ResponseEntity<>(exceptionsResponse, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(UserExistsException.class)
  public ResponseEntity<ExceptionResponse> handleUserExistsException(Exception exception, WebRequest webRequest){

    ExceptionResponse exceptionsResponse = new ExceptionResponse(
      exception.getMessage(),
      new Date(),
      webRequest.getDescription(false)
    );
    return new ResponseEntity<>(exceptionsResponse, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(TransactionDeniedException.class)
  public ResponseEntity<ExceptionResponse> handleTransactionDeniedException(Exception exception, WebRequest webRequest){

    ExceptionResponse exceptionsResponse = new ExceptionResponse(
      exception.getMessage(),
      new Date(),
      webRequest.getDescription(false)
    );
    return new ResponseEntity<>(exceptionsResponse, HttpStatus.BAD_REQUEST);
  }

}
