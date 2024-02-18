package com.rosendo.transferSystem.domain.notifications.services;

import com.rosendo.transferSystem.domain.notifications.dtos.NotificationDto;
import com.rosendo.transferSystem.exceptions.TransactionDeniedException;
import com.rosendo.transferSystem.domain.users.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServices {

    @Autowired
    RestTemplate restTemplate;

    public void sendNotification(UserModel user){
        String messageSuccessful = "Transaction finished with successful!";

        NotificationDto notificationRequest = new NotificationDto(user);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity(
                "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6",
                notificationRequest,
                String.class
        );

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            throw new TransactionDeniedException("The notification system is down! ");
        }
    }
}
