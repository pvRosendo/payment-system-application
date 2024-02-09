package com.rosendo.transferSystem.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.rosendo.transferSystem.models.UserModel;
import com.rosendo.transferSystem.models.UserTypeEnum;
import com.rosendo.transferSystem.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
  RestTemplate restTemplate;

  @Autowired
  UserRepository userRepository;

  public List<TransactionModel> getAllTransactions(){
    return transactionRepository.findAll();
  }

  public TransactionModel getTransactionById(UUID transactionId){
    return transactionRepository.findById(transactionId)
                                  .orElseThrow(() -> new ResourceNotFoundException("Transaction not found!"));
  }

  public TransactionModel createTransaction(TransactionDto transactionDto) throws Exception{

    if(userTypeAndBalanceVerification(transactionDto.senderDocument(), transactionDto.balanceTransaction()))
    if(authorizedTransaction())
   
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

  public Boolean userTypeAndBalanceVerification(String senderUserDocument, BigDecimal userBalance) throws Exception{
    var senderUser = userRepository.getByUserDocument(senderUserDocument);
    
    if (senderUser.getUserType() != UserTypeEnum.commonUser){
      throw new TransactionDeniedException("You don't have permission for realizing transactions");
    }
    
    if (senderUser.getUserBalance().compareTo(userBalance) < 0){
      throw new TransactionDeniedException("You don't have enough balance to carry out this transaction");
    }

    return true;
    
  }

  public Boolean authorizedTransaction(){
    
    var authorizationResponse = restTemplate.getForEntity(
      "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", 
      Map.class
    );
    
    String message = (String) authorizationResponse.getBody().get("message");

    if(authorizationResponse.getStatusCode() == HttpStatus.OK && !"Autorizado".equals(message)){
      throw new TransactionDeniedException("Transaction don't authorized!");
    }
    return true;
  }

}
