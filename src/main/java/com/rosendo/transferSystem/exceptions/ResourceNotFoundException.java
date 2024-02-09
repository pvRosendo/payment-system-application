package com.rosendo.transferSystem.exceptions;

import java.io.Serial;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

  @Serial
  private final static long serialVersionUID = 1L;

  public ResourceNotFoundException(String message){
    super(message);
  }

}
