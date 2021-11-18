package com.u238.training.service;

import com.u238.training.entity.User;

import java.util.List;

public interface UserService {

    List getUsers();

    void saveUser(User user);

    User getUser(String username);

    void deleteUser(String username);

    List searchUser(String theSearchName);
}
