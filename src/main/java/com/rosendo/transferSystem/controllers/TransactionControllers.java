package com.rosendo.transferSystem.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.rosendo.transferSystem.dtos.TransactionDto;
import com.rosendo.transferSystem.models.TransactionModel;
import com.rosendo.transferSystem.services.TransactionServices;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(value = "/internalSystem/transactions")
public class TransactionControllers {

  @Autowired
  TransactionServices transactionServices;
  
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TransactionModel> createTransaction(@RequestBody @Valid TransactionDto TransactionDto){
    return ResponseEntity.status(HttpStatus.CREATED).body(transactionServices.createTransaction(TransactionDto));
  }
}
