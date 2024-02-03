package com.rosendo.transferSystem.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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

  public Boolean findByUserIdentification(UserDto userDto){

    var newUserIdentification = userDto.userIdentification();

    List<UserModel> listOfUsers = userRepository.findByUserIdentification(newUserIdentification);

    return listOfUsers.size() > 0 ? true : false;
    
  }
  
  public Boolean findByUserEmail(UserDto userDto){

    var newUserIdentification = userDto.userEmail();

    List<UserModel> listOfUsers = userRepository.findByUserEmail(newUserIdentification);

    return listOfUsers.size() > 0 ? true : false;
    
  }


}
