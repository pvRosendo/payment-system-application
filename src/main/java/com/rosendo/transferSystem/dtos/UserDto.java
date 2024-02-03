package com.rosendo.transferSystem.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDto(
  @NotBlank String userName,
  @NotBlank String userDocument,
  @NotBlank String userEmail,
  @NotBlank String userPassword
) {}
