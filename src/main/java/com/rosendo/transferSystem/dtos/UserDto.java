package com.rosendo.transferSystem.dtos;

import com.rosendo.transferSystem.models.UserTypeEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
  @NotBlank String userName,
  @NotBlank String userDocument,
  @NotBlank String userEmail,
  @NotBlank String userPassword,
  @NotNull UserTypeEnum userType
) {}
