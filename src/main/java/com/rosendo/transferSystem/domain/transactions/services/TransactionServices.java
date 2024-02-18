package com.rosendo.transferSystem.domain.transactions.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import com.rosendo.transferSystem.domain.users.models.UserModel;
import com.rosendo.transferSystem.domain.users.repositories.UserRepository;

import com.rosendo.transferSystem.domain.notifications.services.NotificationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rosendo.transferSystem.domain.transactions.dtos.TransactionDtoRequest;
import com.rosendo.transferSystem.exceptions.ResourceNotFoundException;
import com.rosendo.transferSystem.domain.transactions.models.StatusTransactionEnum;
import com.rosendo.transferSystem.domain.transactions.models.TransactionModel;
import com.rosendo.transferSystem.domain.transactions.repositories.TransactionRepository;

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

  public TransactionModel createTransaction(TransactionDtoRequest transactionDtoRequest){

    var senderUser = userRepository.getByUserDocument(transactionDtoRequest.senderDocument());
    var receiverUser = userRepository.getByUserDocument(transactionDtoRequest.senderDocument());

    verificationService.userTypeAndBalanceVerification(transactionDtoRequest.senderDocument(), transactionDtoRequest.balanceTransaction());
    verificationService.authorizedTransaction();

    updateUserModelTransaction(
      transactionDtoRequest.senderDocument(),
      transactionDtoRequest.receiverDocument(),
      transactionDtoRequest.balanceTransaction()
    );

    var transaction = new TransactionModel();
    transaction.setSenderDocumentTransaction(transactionDtoRequest.senderDocument());
    transaction.setReceiverDocumentTransaction(transactionDtoRequest.receiverDocument());
    transaction.setBalanceTransaction(transactionDtoRequest.balanceTransaction());
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
