package com.rosendo.transferSystem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionDtoResponse(
        @NotBlank String senderDocument,
        @NotBlank String receiverDocument,
        @NotNull BigDecimal balanceTransaction,
        @NotBlank String statusTransaction
) {}
