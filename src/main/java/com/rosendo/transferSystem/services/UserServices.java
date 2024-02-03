package com.rosendo.transferSystem.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Arrays;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.rosendo.transferSystem.dtos.UserDto;
import com.rosendo.transferSystem.models.UserModel;
import com.rosendo.transferSystem.repositories.UserRepository;

//TODO implement exception

@Service
public class UserServices {

  @Autowired
  UserRepository userRepository;

  public UserModel getUserById(UUID userId){
    return userRepository.findById(userId).orElseThrow();
  }

  public List<UserModel> getAllUsers(){
    List<UserModel> listUsers = userRepository.findAll();
    return listUsers;
  }

  public UserModel createUser(UserDto userDto){
    
    var userModel = new UserModel();
    userModel.setUserBalance(BigDecimal.ZERO);

    BeanUtils.copyProperties(userDto, userModel);
    return userRepository.save(userModel);

  }
  
  public UserModel updateModel(UUID userId, UserDto userDto){
    
    UserModel userModel = userRepository.findById(userId).orElseThrow();
    BeanUtils.copyProperties(userDto, userModel);
    
    return userRepository.save(userModel);
  }

  public void deleteUserById(UUID userId) {
    userRepository.delete(userRepository.findById(userId).orElseThrow());
  }

  public Boolean findByUserDocument(UserDto userDto){

    var newUserDocument = userDto.userDocument();

    List<UserModel> listOfUsers = userRepository.findByUserDocument(newUserDocument);

    return listOfUsers.size() > 0 ? true : false;
    
  }
  
  public Boolean findByUserEmail(UserDto userDto){

    var newUserDocument = userDto.userEmail();

    List<UserModel> listOfUsers = userRepository.findByUserEmail(newUserDocument);

    return listOfUsers.size() > 0 ? true : false;
    
  }

  public List<UserModel> updateUserModelTransaction(String userSenderDocument, String userReceiverDocument, BigDecimal userBalance){
    UserModel userSenderModel = userRepository.getByUserDocument(userSenderDocument);
    userSenderModel.setUserBalance(userBalance);
    
    UserModel userReceiverModel = userRepository.getByUserDocument(userReceiverDocument);
    userReceiverModel.setUserBalance(userBalance.negate());

    List<UserModel> updateListUsers = new ArrayList<>(Arrays.asList(userSenderModel, userReceiverModel));
    return userRepository.saveAll(updateListUsers);
  }


}
