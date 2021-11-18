package com.u238.training.dao;

import com.u238.training.entity.User;
import java.util.List;

public interface UserDAO{

     List<User> getUsers();

     void saveUser(User user);

     User getUser(String username);

     void deleteUser(String username);

     List searchUser(String theSearchName);

}
