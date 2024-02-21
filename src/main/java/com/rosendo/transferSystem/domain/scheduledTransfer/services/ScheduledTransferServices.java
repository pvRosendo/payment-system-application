package com.rosendo.transferSystem.domain.scheduledTransfer.services;

import com.rosendo.transferSystem.domain.scheduledTransfer.dtos.ScheduledTransferDto;
import com.rosendo.transferSystem.domain.scheduledTransfer.models.ScheduledTransferModel;
import com.rosendo.transferSystem.domain.scheduledTransfer.repository.ScheduledTransferRepository;
import com.rosendo.transferSystem.domain.transactions.models.StatusTransactionEnum;
import com.rosendo.transferSystem.domain.transactions.models.TransactionModel;
import com.rosendo.transferSystem.domain.transactions.services.VerificationAndAuthorizationServices;
import com.rosendo.transferSystem.domain.users.repositories.UserRepository;
import com.rosendo.transferSystem.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ScheduledTransferServices { //TODO finish logic of verify the schedules transfers

    @Autowired
    ScheduledTransferRepository scheduledTransferRepository;

    @Autowired
    VerificationAndAuthorizationServices verificationService;

    public ScheduledTransferModel getScheduledTransferById(UUID idScheduledTransfer){
        return scheduledTransferRepository.findById(idScheduledTransfer)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found!"));
    }
    public ScheduledTransferModel createScheduledTransfer(ScheduledTransferDto scheduledTransferDto){

        System.out.println(scheduledTransferDto);

        verificationService.userTypeAndBalanceVerification(
                scheduledTransferDto.senderDocument(),
                scheduledTransferDto.balanceTransaction()
        );
        verificationService.authorizedTransaction();

        var scheduledTransferModel = new ScheduledTransferModel();

        scheduledTransferModel.setSenderScheduledTransfer(scheduledTransferDto.senderDocument());
        scheduledTransferModel.setReceiverScheduledTransfer(scheduledTransferDto.receiverDocument());
        scheduledTransferModel.setBalanceTransaction(scheduledTransferDto.balanceTransaction());
        scheduledTransferModel.setStatusTransaction(StatusTransactionEnum.inProgress);
        scheduledTransferModel.setTimeStamp(scheduledTransferDto.dataTransfer());

        return scheduledTransferRepository.save(scheduledTransferModel);
    }
}
