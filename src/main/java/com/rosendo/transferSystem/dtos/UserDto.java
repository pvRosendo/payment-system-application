package com.rosendo.transferSystem.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDto(
  @NotBlank String userName,
  @NotBlank String userIdentification,
  @NotBlank String userEmail,
  @NotBlank String userPassword
) {}
