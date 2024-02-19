package com.rosendo.transferSystem.domain.scheduledTransfer.repository;

import com.rosendo.transferSystem.domain.scheduledTransfer.models.ScheduledTransferModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduledTransferRepository extends JpaRepository<ScheduledTransferModel, UUID> {}
