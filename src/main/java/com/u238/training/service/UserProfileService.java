package com.u238.training.service;

import com.u238.training.entity.UserProfile;

import java.util.List;

public interface UserProfileService {
    List getUserProfiles();

    UserProfile getUserProfile(int id);

    void deleteUserProfile(int id);

    List searchUserProfiles(String theSearchName);

    List getUserProfiles(int theSortField);

     void saveUserProfile(UserProfile userProfile);
}
