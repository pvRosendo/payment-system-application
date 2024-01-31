package com.rosendo.transferSystem.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosendo.transferSystem.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID>{}
