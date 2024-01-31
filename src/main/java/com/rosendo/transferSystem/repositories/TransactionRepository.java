package com.rosendo.transferSystem.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosendo.transferSystem.models.TransactionModel;

public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {}
