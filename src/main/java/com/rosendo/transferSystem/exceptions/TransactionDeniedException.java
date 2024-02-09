package com.rosendo.transferSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionDeniedException extends RuntimeException{

  @Serial
  private static final long serialVersionUID = 1L;

  public TransactionDeniedException(String message){
    super(message);
  }

}
