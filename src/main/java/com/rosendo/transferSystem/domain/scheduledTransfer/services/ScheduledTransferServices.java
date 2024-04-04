package com.rosendo.transferSystem.domain.scheduledTransfer.services;

import com.rosendo.transferSystem.domain.scheduledTransfer.dtos.ScheduledTransferDto;
import com.rosendo.transferSystem.domain.scheduledTransfer.models.ScheduledTransferModel;
import com.rosendo.transferSystem.domain.scheduledTransfer.repository.ScheduledTransferRepository;
import com.rosendo.transferSystem.domain.transactions.dtos.TransactionDtoRequest;
import com.rosendo.transferSystem.domain.transactions.services.TransactionServices;
import com.rosendo.transferSystem.domain.transactions.services.VerificationAndAuthorizationServices;
import com.rosendo.transferSystem.infrastructure.configs.ScheduledConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    private final Logger logger = Logger.getLogger(ScheduledTransferServices.class.getName());

    public List<ScheduledTransferModel> findAllScheduledTransfers(){

        logger.info("Collecting and create the list with transactions.");
        return scheduledTransferRepository.findAll();
    }

    public List<ScheduledTransferModel> getScheduledTransfersByTimestamp(LocalDateTime timeStamp){
        logger.info("Finding the scheduled transaction by timestamp.");
        return scheduledTransferRepository.findByTimeStamp(timeStamp);
    }

    public ScheduledTransferModel createScheduledTransfer(ScheduledTransferDto scheduledTransferDto){

        logger.info("Verifying the balance of the sender user.");
        verificationService.userTypeAndBalanceVerification(
                scheduledTransferDto.senderDocument(),
                scheduledTransferDto.balanceTransaction()
        );

        var scheduledTransferModel = new ScheduledTransferModel();

        logger.info("Setting the model of transaction for database.");
        scheduledTransferModel.setSenderScheduledTransfer(scheduledTransferDto.senderDocument());
        scheduledTransferModel.setReceiverScheduledTransfer(scheduledTransferDto.receiverDocument());
        scheduledTransferModel.setBalanceTransaction(scheduledTransferDto.balanceTransaction());
        scheduledTransferModel.setStatusTransaction(inProgress);
        scheduledTransferModel.setTimeStamp(parserDate(scheduledTransferDto));

        logger.info("Saving the new scheduled transaction in database");
        return scheduledTransferRepository.save(scheduledTransferModel);
    }

    @Scheduled(fixedDelay = SECOND, zone = TIME_ZONE)
    public void realizeScheduledTransfer(){

        logger.info("Running the automatic function for verify scheduled transactions.");
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
