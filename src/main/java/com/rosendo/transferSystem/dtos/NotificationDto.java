package com.rosendo.transferSystem.dtos;

import com.rosendo.transferSystem.models.UserModel;
import jakarta.validation.constraints.NotBlank;

public record NotificationDto(@NotBlank UserModel user) {}
