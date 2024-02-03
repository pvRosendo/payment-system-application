package com.rosendo.transferSystem.dtos;

import java.math.BigDecimal;

import com.rosendo.transferSystem.models.UserModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionDto(
  @NotBlank UserModel senderTransaction,
  @NotBlank UserModel receiverTransaction,
  @NotNull BigDecimal balanceTransaction
) {

}
