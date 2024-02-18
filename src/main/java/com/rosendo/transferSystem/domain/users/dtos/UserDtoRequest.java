package com.rosendo.transferSystem.domain.users.dtos;

import com.rosendo.transferSystem.domain.users.models.UserTypeEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDtoRequest(
  @NotBlank String userName,
  @NotBlank String userDocument,
  @NotBlank String userEmail,
  @NotBlank String userPassword,
  @NotNull UserTypeEnum userType
) {}
