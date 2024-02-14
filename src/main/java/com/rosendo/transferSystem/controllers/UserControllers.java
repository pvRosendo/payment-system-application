package com.rosendo.transferSystem.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rosendo.transferSystem.dtos.UserDtoRequest;
import com.rosendo.transferSystem.models.UserModel;
import com.rosendo.transferSystem.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/internalSystem/users")
public class UserControllers {

  @Autowired
  UserServices userServices;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserModel>> getAllUsers(){
    return ResponseEntity.status(HttpStatus.OK).body(userServices.getAllUsers());
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getUserById(@PathVariable(value = "id") UUID userId){
    return ResponseEntity.status(HttpStatus.OK).body(userServices.getUserById(userId));
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createUser(@RequestBody @Valid UserDtoRequest userDtoRequest){
    return ResponseEntity.status(HttpStatus.CREATED).body(userServices.createUser(userDtoRequest));
  }
  
  @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> updateUser(
    @PathVariable(value = "id") UUID userId,
    @RequestBody @Valid UserDtoRequest userDtoRequest
  ){
    return ResponseEntity.status(HttpStatus.OK).body(userServices.updateModel(userId, userDtoRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") UUID userId) {
    userServices.deleteUserById(userId);
    return ResponseEntity.noContent().build();
  }
}
