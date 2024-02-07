package com.rosendo.transferSystem.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionDto(
  @NotBlank String senderDocument,
  @NotBlank String receiverDocument,
  @NotNull BigDecimal balanceTransaction
) {}
