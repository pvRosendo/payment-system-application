package com.rosendo.transferSystem.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import com.rosendo.transferSystem.models.UserModel;
import com.rosendo.transferSystem.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rosendo.transferSystem.dtos.TransactionDto;
import com.rosendo.transferSystem.exceptions.ResourceNotFoundException;
import com.rosendo.transferSystem.models.StatusTransactionEnum;
import com.rosendo.transferSystem.models.TransactionModel;
import com.rosendo.transferSystem.repositories.TransactionRepository;

@Service
public class TransactionServices {

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  NotificationServices notificationServices;

  @Autowired
  VerificationAndAuthorizationServices verificationService;

  public List<TransactionModel> getAllTransactions(){
    return transactionRepository.findAll();
  }

  public TransactionModel getTransactionById(UUID transactionId){
    return transactionRepository.findById(transactionId)
                                  .orElseThrow(() -> new ResourceNotFoundException("Transaction not found!"));
  }

  public TransactionModel createTransaction(TransactionDto transactionDto){

    var senderUser = userRepository.getByUserDocument(transactionDto.senderDocument());
    var receiverUser = userRepository.getByUserDocument(transactionDto.senderDocument());

    verificationService.userTypeAndBalanceVerification(transactionDto.senderDocument(), transactionDto.balanceTransaction());
    verificationService.authorizedTransaction();

    updateUserModelTransaction(
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

    notificationServices.sendNotification(senderUser);
    notificationServices.sendNotification(receiverUser);

    return transactionRepository.save(transaction);
  }

  public void updateUserModelTransaction(String userSenderDocument, String userReceiverDocument, BigDecimal userBalance){

    UserModel userSenderModel = userRepository.getByUserDocument(userSenderDocument);
    userSenderModel.setUserBalance(userSenderModel.getUserBalance().subtract(userBalance));

    UserModel userReceiverModel = userRepository.getByUserDocument(userReceiverDocument);
    userReceiverModel.setUserBalance(userReceiverModel.getUserBalance().add(userBalance));

    List<UserModel> updateListUsers = new ArrayList<>(Arrays.asList(userSenderModel, userReceiverModel));
    userRepository.saveAll(updateListUsers);
  }

}
