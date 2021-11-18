package com.u238.training.utils;

import com.u238.training.entity.User;
import com.u238.training.entity.UserI;
import com.u238.training.entity.UserProfile;
import com.u238.training.entity.UserProfileI;

import javax.validation.Valid;

public class UserRegisterUtil implements UserI, UserProfileI {
    @Valid
    private User user;
    @Valid
    private UserProfile userProfile;

    public UserRegisterUtil() {
        user=new User();
        userProfile=new UserProfile();
        user.setUserProfile(userProfile);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public UserProfile getUserProfile() {
        return userProfile;
    }

    @Override
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public void setUsername(String username) {
        this.user.setUsername(username);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public void setPassword(String password) {
        this.user.setPassword(password);
    }

    @Override
    public String getConfirmPassword() {
        return user.getConfirmPassword();
    }

    @Override
    public void setConfirmPassword(String confirmPassword) {
        this.user.setConfirmPassword(confirmPassword);
    }

    @Override
    public String getFirstName() {
        return userProfile.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        this.userProfile.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return userProfile.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        this.userProfile.setLastName(lastName);
    }

    @Override
    public String getEmail() {
        return userProfile.getEmail();
    }

    @Override
    public void setEmail(String email) {
        this.userProfile.setEmail(email);
    }

}
