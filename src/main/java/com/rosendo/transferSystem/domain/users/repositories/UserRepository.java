package com.rosendo.transferSystem.domain.users.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosendo.transferSystem.domain.users.models.UserModel;


public interface UserRepository extends JpaRepository<UserModel, UUID>{

  List<UserModel> findByUserDocument(String userDocument);
  List<UserModel> findByUserEmail(String userEmail);
  UserModel getByUserDocument(String userDocument);

}

