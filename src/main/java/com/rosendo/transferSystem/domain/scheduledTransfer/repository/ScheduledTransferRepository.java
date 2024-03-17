package com.rosendo.transferSystem.domain.scheduledTransfer.repository;

import com.rosendo.transferSystem.domain.scheduledTransfer.models.ScheduledTransferModel;
import com.rosendo.transferSystem.domain.transactions.models.StatusTransactionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScheduledTransferRepository extends JpaRepository<ScheduledTransferModel, UUID> {
    ScheduledTransferModel getScheduledTransferByStatus(StatusTransactionEnum status);
}
