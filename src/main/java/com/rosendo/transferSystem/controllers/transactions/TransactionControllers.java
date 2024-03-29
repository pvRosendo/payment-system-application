package com.rosendo.transferSystem.controllers.transactions;

import com.rosendo.transferSystem.domain.transactions.dtos.TransactionDtoRequest;
import org.springframework.web.bind.annotation.RestController;

import com.rosendo.transferSystem.domain.transactions.models.TransactionModel;
import com.rosendo.transferSystem.domain.transactions.services.TransactionServices;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(value = "/internalSystem/transactions")
public class TransactionControllers {

  @Autowired
  TransactionServices transactionServices;
  
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TransactionModel>> getAllTransactions(){
    return ResponseEntity.status(HttpStatus.OK).body(transactionServices.getAllTransactions());
  }
  
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TransactionModel> getTransactionById(@PathVariable(value = "id") UUID transactionId){
    return ResponseEntity.status(HttpStatus.OK).body(transactionServices.getTransactionById(transactionId));
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TransactionModel> createTransaction(@RequestBody @Valid TransactionDtoRequest TransactionDtoRequest){
    return ResponseEntity.status(HttpStatus.CREATED).body(transactionServices.createTransaction(TransactionDtoRequest));
  }
}
