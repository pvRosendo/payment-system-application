package com.rosendo.paymentSystem.repositories;

import com.rosendo.paymentSystem.models.StorekeeperModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StorekeeperRepository extends JpaRepository<StorekeeperModel, UUID> {}
