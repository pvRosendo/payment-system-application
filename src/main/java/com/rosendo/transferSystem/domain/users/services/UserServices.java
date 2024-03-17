package com.rosendo.transferSystem.domain.users.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.rosendo.transferSystem.controllers.users.UserControllers;
import com.rosendo.transferSystem.domain.users.dtos.UserDtoResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.rosendo.transferSystem.domain.users.dtos.UserDtoRequest;
import com.rosendo.transferSystem.exceptions.ResourceNotFoundException;
import com.rosendo.transferSystem.exceptions.UserExistsException;
import com.rosendo.transferSystem.domain.users.models.UserModel;
import com.rosendo.transferSystem.domain.users.repositories.UserRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserServices {

  @Autowired
  UserRepository userRepository;

  public UserDtoResponse getUserById(UUID userId){
    var user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

    user.add(linkTo(methodOn(UserControllers.class).getAllUsers()).withRel("List of users"));

    return new UserDtoResponse(
          user.getUserName(),
          user.getUserEmail(),
          user.getUserType()
    );
  }

  public List<UserModel> getAllUsers(){
    List<UserModel> listOfUsers = userRepository.findAll();
    for (UserModel user : listOfUsers){
      user.add(linkTo(methodOn(UserControllers.class).getUserById(user.getId())).withSelfRel());
    }
    return listOfUsers;
  }

  public UserDtoResponse createUser(UserDtoRequest userDtoRequest){

    if (findByUserDocument(userDtoRequest) || findByUserEmail(userDtoRequest)){
      throw new UserExistsException("There is already a user with this document or email!");
    }

    UserDtoResponse response = new UserDtoResponse(
            userDtoRequest.userName(),
            userDtoRequest.userEmail(),
            userDtoRequest.userType()
    );

    var userModel = new UserModel();
    userModel.setUserBalance(BigDecimal.ZERO);

    BeanUtils.copyProperties(userDtoRequest, userModel);
    userModel.add(linkTo(methodOn(UserControllers.class).getUserById(userModel.getId())).withSelfRel());

    userRepository.save(userModel);

    return response;
  }
  
  public UserDtoResponse updateModel(UUID userId, UserDtoRequest userDtoRequest){
    
    UserModel userModel = userRepository.findById(userId)
                          .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    BeanUtils.copyProperties(userDtoRequest, userModel);
    userModel.add(linkTo(methodOn(UserControllers.class).getUserById(userModel.getId())).withSelfRel());

    userRepository.save(userModel);

    return new UserDtoResponse(
            userDtoRequest.userName(),
            userDtoRequest.userEmail(),
            userDtoRequest.userType()
    );
  }

  public UserModel updateUserBalance(UUID userId, BigDecimal newBalance){
    UserModel userModel = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

    userModel.setUserBalance(newBalance);
    userModel.add(linkTo(methodOn(UserControllers.class).getUserById(userModel.getId())).withSelfRel());

    return userRepository.save(userModel);
  }

  public void deleteUserById(UUID userId) {
    userRepository.delete(userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found!")));
  }

  public boolean findByUserDocument(UserDtoRequest userDtoRequest){
    List<UserModel> listOfUsers = userRepository.findByUserDocument(userDtoRequest.userDocument());
    return !listOfUsers.isEmpty();
  }
  
  public boolean findByUserEmail(UserDtoRequest userDtoRequest){
    List<UserModel> listOfUsers = userRepository.findByUserEmail(userDtoRequest.userEmail());
    return !listOfUsers.isEmpty();
  }

}
