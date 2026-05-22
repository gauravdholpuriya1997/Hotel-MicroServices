package com.lcwd.user.service.service;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {

    // USER OPERATION

    // CREATE

    User saveUserUser(User user);

    User saveUser(User user);

    // GET ALL USER
    List<User> getAllUser();

    // GET SINGLE USER OF GIVEN USERID

    User getUser(String userId);

    //TODO: delete
    // TODO: update

}
