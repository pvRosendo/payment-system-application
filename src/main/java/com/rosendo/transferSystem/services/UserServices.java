package com.rosendo.transferSystem.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.rosendo.transferSystem.dtos.UserDto;
import com.rosendo.transferSystem.exceptions.ResourceNotFoundException;
import com.rosendo.transferSystem.exceptions.UserExistsException;
import com.rosendo.transferSystem.models.UserModel;
import com.rosendo.transferSystem.repositories.UserRepository;

@Service
public class UserServices {

  @Autowired
  UserRepository userRepository;

  public UserModel getUserById(UUID userId){
    return userRepository.findById(userId)
                          .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
  }

  public List<UserModel> getAllUsers(){
    return userRepository.findAll();
  }

  public UserModel createUser(UserDto userDto){

    if (findByUserDocument(userDto) || findByUserEmail(userDto)){
      throw new UserExistsException("There is already a user with this document or email!");
    }

    var userModel = new UserModel();
    userModel.setUserBalance(BigDecimal.ZERO);

    BeanUtils.copyProperties(userDto, userModel);
    return userRepository.save(userModel);

  }
  
  public UserModel updateModel(UUID userId, UserDto userDto){
    
    UserModel userModel = userRepository.findById(userId)
                          .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

    BeanUtils.copyProperties(userDto, userModel);
    return userRepository.save(userModel);
  }

  public void deleteUserById(UUID userId) {
    userRepository.delete(userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found!")));
  }

  public boolean findByUserDocument(UserDto userDto){
    List<UserModel> listOfUsers = userRepository.findByUserDocument(userDto.userDocument());
    return !listOfUsers.isEmpty();
  }
  
  public boolean findByUserEmail(UserDto userDto){
    List<UserModel> listOfUsers = userRepository.findByUserEmail(userDto.userEmail());
    return !listOfUsers.isEmpty();
  }

}
