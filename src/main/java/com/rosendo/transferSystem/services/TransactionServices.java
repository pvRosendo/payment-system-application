package com.rosendo.transferSystem.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import com.rosendo.transferSystem.models.UserModel;
import com.rosendo.transferSystem.models.UserTypeEnum;
import com.rosendo.transferSystem.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rosendo.transferSystem.dtos.TransactionDto;
import com.rosendo.transferSystem.exceptions.ResourceNotFoundException;
import com.rosendo.transferSystem.exceptions.TransactionDeniedException;
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

    userTypeAndBalanceVerification(transactionDto.senderDocument(), transactionDto.balanceTransaction());
    authorizedTransaction();

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

  public void userTypeAndBalanceVerification(String senderUserDocument, BigDecimal userBalance) {
    var senderUser = userRepository.getByUserDocument(senderUserDocument);

    if (senderUser.getUserType() != UserTypeEnum.commonUser){
      throw new TransactionDeniedException("You don't have permission for realizing transactions");
    }

    if (senderUser.getUserBalance().compareTo(userBalance) < 0){
      throw new TransactionDeniedException("You don't have enough balance to carry out this transaction");
    }

  }

  public void authorizedTransaction(){

    var authorizationResponse = restTemplate.getForEntity(
            "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc",
            Map.class
    );

    String message = (String) Objects.requireNonNull(authorizationResponse.getBody()).get("message");

    if(authorizationResponse.getStatusCode() == HttpStatus.OK && !"Autorizado".equals(message)){
      throw new TransactionDeniedException("Transaction don't authorized!");
    }
  }


}
