package com.rosendo.transferSystem.domain.notifications.dtos;

import com.rosendo.transferSystem.domain.users.models.UserModel;
import jakarta.validation.constraints.NotBlank;

public record NotificationDto(@NotBlank UserModel user) {}
