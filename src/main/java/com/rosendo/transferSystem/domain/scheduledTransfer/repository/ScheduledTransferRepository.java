package com.rosendo.transferSystem.domain.scheduledTransfer.repository;

import com.rosendo.transferSystem.domain.scheduledTransfer.models.ScheduledTransferModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ScheduledTransferRepository extends JpaRepository<ScheduledTransferModel, UUID> {
    List<ScheduledTransferModel> findByTimeStamp(LocalDateTime timeStamp);
    ScheduledTransferModel getByTimeStamp(LocalDateTime timeStamp);
}
