package com.rosendo.transferSystem.domain.transactions.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionDtoRequest(
  @NotBlank String senderDocument,
  @NotBlank String receiverDocument,
  @NotNull BigDecimal balanceTransaction
) {}
