package com.rosendo.transferSystem.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosendo.transferSystem.models.UserModel;


public interface UserRepository extends JpaRepository<UserModel, UUID>{
  
  List<UserModel> findByUserIdentification(String userIdentification);
  List<UserModel> findByUserEmail(String userEmail);

}

