package com.rosendo.transferSystem.domain.scheduledTransfer.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ScheduledTransferDto(
        @NotBlank String senderDocument,
        @NotBlank String receiverDocument,
        @NotNull BigDecimal balanceTransaction,
        @NotNull LocalDateTime timeStamp
) {}
