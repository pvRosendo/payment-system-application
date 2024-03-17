package com.rosendo.transferSystem.domain.scheduledTransfer.services;

import com.rosendo.transferSystem.domain.scheduledTransfer.dtos.ScheduledTransferDto;
import com.rosendo.transferSystem.domain.scheduledTransfer.models.ScheduledTransferModel;
import com.rosendo.transferSystem.domain.scheduledTransfer.repository.ScheduledTransferRepository;
import com.rosendo.transferSystem.domain.transactions.dtos.TransactionDtoRequest;
import com.rosendo.transferSystem.domain.transactions.models.StatusTransactionEnum;
import com.rosendo.transferSystem.domain.transactions.services.TransactionServices;
import com.rosendo.transferSystem.domain.transactions.services.VerificationAndAuthorizationServices;
import com.rosendo.transferSystem.exceptions.ResourceNotFoundException;
import com.rosendo.transferSystem.infrastructure.configs.ScheduledConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

        var scheduledTransferModel = new ScheduledTransferModel();

        scheduledTransferModel.setSenderScheduledTransfer(scheduledTransferDto.senderDocument());
        scheduledTransferModel.setReceiverScheduledTransfer(scheduledTransferDto.receiverDocument());
        scheduledTransferModel.setBalanceTransaction(scheduledTransferDto.balanceTransaction());
        scheduledTransferModel.setStatusTransaction(inProgress);
        scheduledTransferModel.setTimeStamp(scheduledTransferDto.dataTransfer());

        return scheduledTransferRepository.save(scheduledTransferModel);
    }

    @Scheduled(fixedDelay = HOUR, zone = TIME_ZONE)
    public void realizeScheduledTransfer(ScheduledTransferDto scheduledTransferDto){

        List<ScheduledTransferModel> listTransfers = scheduledTransferRepository.findAll();

        for (ScheduledTransferModel transfer : listTransfers){
            if (transfer.getTimeStamp().equals(LocalDate.now())) {

                transactionServices.createTransaction(
                        new TransactionDtoRequest(
                                scheduledTransferDto.senderDocument(),
                                scheduledTransferDto.receiverDocument(),
                                scheduledTransferDto.balanceTransaction()
                        )
                );
            }
        }
    }

}
