package com.rosendo.transferSystem.exceptions;

import java.io.Serial;

public class TransactionDeniedException extends RuntimeException{

  @Serial
  private static final long serialVersionUID = 1L;

  public TransactionDeniedException(String message){
    super(message);
  }

}
