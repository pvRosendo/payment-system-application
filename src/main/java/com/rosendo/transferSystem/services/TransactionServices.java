package com.rosendo.transferSystem.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rosendo.transferSystem.dtos.TransactionDto;
import com.rosendo.transferSystem.models.StatusTransactionEnum;
import com.rosendo.transferSystem.models.TransactionModel;
import com.rosendo.transferSystem.repositories.TransactionRepository;

@Service
public class TransactionServices {

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  UserServices userServices;

  public List<TransactionModel> getAllTransactions(){
    List<TransactionModel> listOfTransaction = transactionRepository.findAll();
    return listOfTransaction;
  }

  public TransactionModel getTransactionById(UUID transactionId){
    var transaction = transactionRepository.findById(transactionId).orElseThrow();
    return transaction;
  }

  public TransactionModel createTransaction(TransactionDto transactionDto){

    userServices.updateUserModelTransaction(
      transactionDto.senderDocument(),
      transactionDto.receiverDocument(),
      transactionDto.balanceTransaction()
    );

    var transaction = new TransactionModel();
    transaction.setSenderDocumentTransaction(transactionDto.senderDocument());
    transaction.setReceiverDocumentTransaction(transactionDto.receiverDocument());
    transaction.setBalanceTransaction(transactionDto.balanceTransaction());
    transaction.setStatusTransaction(StatusTransactionEnum.successfull);
    transaction.setTimeStamp(LocalDate.now());
    
    return transactionRepository.save(transaction);
  }
}
