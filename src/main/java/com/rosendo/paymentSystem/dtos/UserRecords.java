package com.rosendo.paymentSystem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecords(
        @NotNull String UserID,
        @NotBlank String UserName,
        @NotBlank String UserEmail
        ) {
}
