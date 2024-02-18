package com.rosendo.transferSystem.domain.transactions.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosendo.transferSystem.domain.transactions.models.TransactionModel;

public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {}
