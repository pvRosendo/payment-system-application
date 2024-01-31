package com.rosendo.transferSystem.controllers;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rosendo.transferSystem.models.UserModel;
import com.rosendo.transferSystem.services.UserServices;

@RestController
@RequestMapping("/internalSystem/users")
public class UserControllers {

  @Autowired
  UserServices userServices;

  @GetMapping(value = "/internalSystem/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserModel>> getAllUsers(){
    return ResponseEntity.status(HttpStatus.OK).body(userServices.getAllUsers());
  }

  @GetMapping(value = "/internalSystem/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getUserById(@PathVariable(value = "id") UUID userId){

    return ResponseEntity.status(HttpStatus.OK).body(userServices.getUserById(userId));
  }

  








}
