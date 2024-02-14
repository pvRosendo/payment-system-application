package com.rosendo.transferSystem.exceptions;

import com.rosendo.transferSystem.models.StatusTransactionEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public record ExceptionResponse(
        String message,
        Date timestamp,
        String details,
        StatusTransactionEnum statusTransaction

) implements Serializable{

  @Serial
  private static final long serialVersionUID = 1L;

}