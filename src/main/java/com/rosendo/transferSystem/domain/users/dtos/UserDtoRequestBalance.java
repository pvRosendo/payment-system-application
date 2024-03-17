package com.rosendo.transferSystem.domain.users.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserDtoRequestBalance(
        @NotNull BigDecimal newBalance
        ) {
}
