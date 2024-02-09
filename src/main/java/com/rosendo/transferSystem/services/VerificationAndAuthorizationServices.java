package com.rosendo.transferSystem.services;

import com.rosendo.transferSystem.exceptions.TransactionDeniedException;
import com.rosendo.transferSystem.models.UserTypeEnum;
import com.rosendo.transferSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Service
public class VerificationAndAuthorizationServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

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
