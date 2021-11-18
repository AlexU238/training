package com.u238.training.entity;

public interface UserI {

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);

    public UserProfile getUserProfile();

    public void setUserProfile(UserProfile userProfile);

    public String getConfirmPassword() ;

    public void setConfirmPassword(String confirmPassword);
}
