package com.quizPortal.quizPortal.controller;

import com.quizPortal.quizPortal.model.Entities.User;
import com.quizPortal.quizPortal.model.dto.*;
import com.quizPortal.quizPortal.service.UserService;;
import com.quizPortal.quizPortal.service.UserSessionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserSessionService userSessionService;

    @PostMapping(path="/")
    public BaseResponse<LoginSignupResponse> createUser(@RequestBody CreateUserRequest request){
        LoginSignupResponse user = userService.createUser(request);
        return new BaseResponse<>(HttpStatus.OK.value(), "Success", user);
    }

    @PostMapping(path = "/login")
    public BaseResponse<LoginSignupResponse> userLogin(@RequestBody CreateUserRequest request){
        LoginSignupResponse userSession = userService.userLogin(request);
        return new BaseResponse<>(HttpStatus.OK.value(), "Success", userSession);
    }

    @GetMapping(path = "/profile")
    public BaseResponse<UpdateUserResponse> getUser(@RequestHeader("Authorization")String token){
        if(StringUtils.isBlank(token))
            throw new AccessDeniedException("Token cannot be null.");
        UpdateUserResponse user = userService.getUser(token);
        return new BaseResponse<>(HttpStatus.OK.value(), "Success", user);
    }

    @PostMapping(path = "/update")
    public BaseResponse<UpdateUserResponse> updateUser(@RequestHeader("Authorization") String token, @RequestBody UpdateUserRequest request){
        if(StringUtils.isBlank(token))
            throw new AccessDeniedException("Access Denied");
        UpdateUserResponse user =userService.updateUser(request,token);
        return new BaseResponse<>(HttpStatus.OK.value(), "Success", user);
    }

    @PostMapping(path ="/logout")
    public BaseResponse<User> userLogout(@RequestHeader("Authorization") String token) {
        if(StringUtils.isBlank(token))
            throw new AccessDeniedException("Access Denied");
        userSessionService.userLogout(token);
        return new BaseResponse<>(HttpStatus.OK.value(), "User successfully logout.");//isko puchna h isme data: null extra aa raha h
    }
}
