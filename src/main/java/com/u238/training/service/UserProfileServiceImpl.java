package com.u238.training.service;

import com.u238.training.dao.UserProfileDAO;
import com.u238.training.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService{

    private UserProfileDAO userProfileDAO;

    @Autowired
    public UserProfileServiceImpl(@Qualifier("userProfileDAOImpl") UserProfileDAO userProfileDAO) {
        this.userProfileDAO = userProfileDAO;
    }

    @Override
    @Qualifier("userTransactionManager")
    @Transactional
    public List getUserProfiles() {
        return userProfileDAO.getProfiles();
    }

    @Override
    @Qualifier("userTransactionManager")
    @Transactional
    public UserProfile getUserProfile(int id) {
        return userProfileDAO.getUserProfile(id);
    }

    @Override
    @Transactional
    public void deleteUserProfile(int id) {
        userProfileDAO.deleteUser(id);
    }

    @Override
    @Qualifier("userTransactionManager")
    @Transactional
    public List searchUserProfiles(String theSearchName) {
        return userProfileDAO.searchUserProfile(theSearchName);
    }

    @Override
    @Qualifier("userTransactionManager")
    @Transactional
    public List getUserProfiles(int theSortField) {
        return userProfileDAO.getUserProfiles(theSortField);
    }

    @Override
    @Qualifier("userTransactionManager")
    @Transactional
    public void saveUserProfile(UserProfile userProfile){
        userProfileDAO.saveUserProfile(userProfile);
    }
}
