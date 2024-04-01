package com.rosendo.transferSystem.domain.scheduledTransfer.services;

import com.rosendo.transferSystem.domain.scheduledTransfer.dtos.ScheduledTransferDto;
import com.rosendo.transferSystem.domain.scheduledTransfer.models.ScheduledTransferModel;
import com.rosendo.transferSystem.domain.scheduledTransfer.repository.ScheduledTransferRepository;
import com.rosendo.transferSystem.domain.transactions.dtos.TransactionDtoRequest;
import com.rosendo.transferSystem.domain.transactions.services.TransactionServices;
import com.rosendo.transferSystem.domain.transactions.services.VerificationAndAuthorizationServices;
import com.rosendo.transferSystem.domain.users.dtos.UserDtoRequest;
import com.rosendo.transferSystem.domain.users.models.UserModel;
import com.rosendo.transferSystem.exceptions.ResourceNotFoundException;
import com.rosendo.transferSystem.infrastructure.configs.ScheduledConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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

    public ScheduledTransferModel getScheduledTransferById(UUID idScheduledTransfer){
        return scheduledTransferRepository.findById(idScheduledTransfer)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found!"));
    }

    public ScheduledTransferModel createScheduledTransfer(ScheduledTransferDto scheduledTransferDto){

        verificationService.userTypeAndBalanceVerification(
                scheduledTransferDto.senderDocument(),
                scheduledTransferDto.balanceTransaction()
        );
        verificationService.authorizedTransaction();

        System.out.println(scheduledTransferDto.timeStamp());

        var scheduledTransferModel = new ScheduledTransferModel();

        scheduledTransferModel.setSenderScheduledTransfer(scheduledTransferDto.senderDocument());
        scheduledTransferModel.setReceiverScheduledTransfer(scheduledTransferDto.receiverDocument());
        scheduledTransferModel.setBalanceTransaction(scheduledTransferDto.balanceTransaction());
        scheduledTransferModel.setStatusTransaction(inProgress);
        scheduledTransferModel.setTimeStamp(parserDate(scheduledTransferDto));

        return scheduledTransferRepository.save(scheduledTransferModel);
    }

    @Scheduled(fixedDelay = MINUTE, zone = TIME_ZONE)
    public void realizeScheduledTransfer(){

        LocalDateTime realTime = LocalDateTime.now();
        logger.info(realTime.toString());

        ScheduledTransferModel transfer = scheduledTransferRepository.getByTimeStamp(realTime);

        logger.info("searching the scheduledTransfer: " + transfer);


        if (findByTimestamp(realTime)){
            transactionServices.createTransaction(
                    new TransactionDtoRequest(
                            transfer.getSenderScheduledTransfer(),
                            transfer.getReceiverScheduledTransfer(),
                            transfer.getBalanceTransaction()
                    )
            );
        }
    }

    public boolean findByTimestamp(LocalDateTime localDateTime){
        List<ScheduledTransferModel> listOfScheduledTransfers =
                scheduledTransferRepository.findByTimeStamp(localDateTime);
        return !listOfScheduledTransfers.isEmpty();
    }

}
