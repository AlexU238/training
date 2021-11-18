package com.u238.training.service;


import com.u238.training.dao.UserDAO;
import com.u238.training.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAOImpl") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Qualifier("userTransactionManager")
    @Transactional
    public List getUsers() {
        return userDAO.getUsers();
    }

    @Override
    @Qualifier("userTransactionManager")
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    @Qualifier("userTransactionManager")
    @Transactional
    public User getUser(String username) {
        return userDAO.getUser(username);
    }

    @Override
    @Qualifier("userTransactionManager")
    @Transactional
    public void deleteUser(String username) {
        userDAO.deleteUser(username);
    }

    @Override
    @Qualifier("userTransactionManager")
    @Transactional
    public List searchUser(String theSearchName) {
        return userDAO.searchUser(theSearchName);
    }
}
