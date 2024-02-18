package com.rosendo.transferSystem.domain.users.dtos;

import com.rosendo.transferSystem.domain.users.models.UserTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDtoResponse(
        @NotBlank String userName,
        @NotBlank String userEmail,
        @NotNull UserTypeEnum userType
) {
}
