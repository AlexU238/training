package com.u238.training.controllers;

import com.u238.training.entity.User;
import com.u238.training.entity.UserProfile;
import com.u238.training.service.UserProfileService;
import com.u238.training.service.UserService;
import com.u238.training.utils.SortUtilsUserProfile;
import com.u238.training.utils.UserRegisterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/userControl")
public class UsersAndProfilesController {

    private UserService userService;
    private UserProfileService userProfileService;

    @Autowired
    public UsersAndProfilesController(@Qualifier("userServiceImpl") UserService userService,
                                      @Qualifier("userProfileServiceImpl") UserProfileService userProfileService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @RequestMapping("/userProfileList")
    public String giveUserProfileList(Model model, @RequestParam(required = false) String sort){

        List userProfileList;

        if (sort != null) {
            int theSortField = Integer.parseInt(sort);
            userProfileList = userProfileService.getUserProfiles(theSortField);
        } else {

            userProfileList = userProfileService.getUserProfiles(SortUtilsUserProfile.LAST_NAME);
        }
        model.addAttribute("userProfiles", userProfileList);

        return "user_list_profiles";
    }

    @RequestMapping("/users")
    public String giveUsers(Model model){
        model.addAttribute("users",userService.getUsers());
        return "user_list_profiles";
    }


    @GetMapping("/register")
    public String createNewUser( Model model){

        UserRegisterUtil userRegisterUtil = new UserRegisterUtil();

        model.addAttribute("registerUser",userRegisterUtil);


        return "user_creation_form";
    }
   //

    @PostMapping("/saveNewUser")
    public String saveNewUser(@Valid @ModelAttribute("registerUser")UserRegisterUtil userRegisterUtil,BindingResult out){


        if(out.hasErrors()){
            return "redirect:/userControl/register";
        }else {
            userService.saveUser(userRegisterUtil.getUser());

            return "redirect:/login";
        }
    }

    @GetMapping("/updateProfile")
    public String updateUserProfile(@RequestParam("userProfileId") int id, Model model){

        model.addAttribute("userProfile",userProfileService.getUserProfile(id));

        return "user_profile_update_form";
    }


    @PostMapping("/saveProfile")
    public String saveUserProfile(@Valid @ModelAttribute("userProfile")UserProfile userProfile,BindingResult out){
        if(out.hasErrors()){
            return "redirect:/userControl/updateProfile";
        }else {
            userProfileService.saveUserProfile(userProfile);

            return "redirect:/userControl/userProfileList";
        }
    }


    //under revision. need more knowledge
//    @GetMapping("/updateProfileSecurity")
//    public String updateUserProfileUsernameAndPassword(){
//
//       // model.addAttribute("userProfile",userProfileService.getUserProfile(id));
//
//        return "user_profile_update_form";
//    }

    @GetMapping("/delete")
    public String deleteProfile(@RequestParam("userProfileId") int id){

        userProfileService.deleteUserProfile(id);

        return "user_list_profiles";
    }

    @GetMapping("/search")
    public String searchUserProfile(@RequestParam("theSearchName") String theSearchName, Model theModel) {

        theModel.addAttribute("userProfiles", userProfileService.searchUserProfiles(theSearchName));

        return "user_list_profiles";
    }

}
