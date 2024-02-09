package com.rosendo.transferSystem.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public record ExceptionResponse(String message, Date timestamp, String details) implements Serializable{

  @Serial
  private static final long serialVersionUID = 1L;

}