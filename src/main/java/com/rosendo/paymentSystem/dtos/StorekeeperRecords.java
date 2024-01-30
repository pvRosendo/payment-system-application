package com.rosendo.paymentSystem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StorekeeperRecords(
        @NotNull String StorekeeperID,
        @NotBlank String StorekeeperName,
        @NotBlank String StorekeeperEmail
) {
}
