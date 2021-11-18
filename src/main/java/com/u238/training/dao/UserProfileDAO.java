package com.u238.training.dao;

import com.u238.training.entity.User;
import com.u238.training.entity.UserProfile;

import java.util.List;

public interface UserProfileDAO {

    List<UserProfile> getProfiles();

    UserProfile getUserProfile(int id);

    void deleteUser(int id);

    List searchUserProfile(String theSearchName);

    List<UserProfile> getUserProfiles(int theSortField);

     void saveUserProfile(UserProfile userProfile);
}
