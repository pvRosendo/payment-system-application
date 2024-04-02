package com.rosendo.transferSystem.domain.scheduledTransfer.services;

import com.rosendo.transferSystem.domain.scheduledTransfer.dtos.ScheduledTransferDto;
import com.rosendo.transferSystem.domain.scheduledTransfer.models.ScheduledTransferModel;
import com.rosendo.transferSystem.domain.scheduledTransfer.repository.ScheduledTransferRepository;
import com.rosendo.transferSystem.domain.transactions.dtos.TransactionDtoRequest;
import com.rosendo.transferSystem.domain.transactions.services.TransactionServices;
import com.rosendo.transferSystem.domain.transactions.services.VerificationAndAuthorizationServices;
import com.rosendo.transferSystem.exceptions.ResourceNotFoundException;
import com.rosendo.transferSystem.infrastructure.configs.ScheduledConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static com.rosendo.transferSystem.domain.transactions.models.StatusTransactionEnum.inProgress;


@Component
@EnableScheduling
@Service
public class ScheduledTransferServices extends ScheduledConfigs {

    @Autowired
    ScheduledTransferRepository scheduledTransferRepository;

    @Autowired
    VerificationAndAuthorizationServices verificationService;

    @Autowired
    TransactionServices transactionServices;
    private Logger logger = Logger.getLogger(ScheduledTransferServices.class.getName());


    public List<ScheduledTransferModel> findAllScheduledTransfers(){
        return scheduledTransferRepository.findAll();
    }

    public List<ScheduledTransferModel> getScheduledTransfersByTimestamp(LocalDateTime timeStamp){
        return scheduledTransferRepository.findByTimeStamp(timeStamp);
    }

    public ScheduledTransferModel createScheduledTransfer(ScheduledTransferDto scheduledTransferDto){

        verificationService.userTypeAndBalanceVerification(
                scheduledTransferDto.senderDocument(),
                scheduledTransferDto.balanceTransaction()
        );

        var scheduledTransferModel = new ScheduledTransferModel();

        scheduledTransferModel.setSenderScheduledTransfer(scheduledTransferDto.senderDocument());
        scheduledTransferModel.setReceiverScheduledTransfer(scheduledTransferDto.receiverDocument());
        scheduledTransferModel.setBalanceTransaction(scheduledTransferDto.balanceTransaction());
        scheduledTransferModel.setStatusTransaction(inProgress);
        scheduledTransferModel.setTimeStamp(parserDate(scheduledTransferDto));

        return scheduledTransferRepository.save(scheduledTransferModel);
    }

    @Scheduled(fixedDelay = SECOND, zone = TIME_ZONE)
    public void realizeScheduledTransfer(){

        LocalDateTime realTimeInSeconds = LocalDateTime.now().withNano(0);

        List<ScheduledTransferModel> listOfTransfers = getScheduledTransfersByTimestamp(realTimeInSeconds);

        for (ScheduledTransferModel transfer : listOfTransfers){
            transactionServices.createTransaction(
                    new TransactionDtoRequest(
                            transfer.getSenderScheduledTransfer(),
                            transfer.getReceiverScheduledTransfer(),
                            transfer.getBalanceTransaction()
                    )
            );
        }
    }
}
