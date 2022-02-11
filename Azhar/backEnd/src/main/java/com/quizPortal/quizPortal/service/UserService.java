package com.quizPortal.quizPortal.service;

import com.quizPortal.quizPortal.model.dto.CreateUserRequest;
import com.quizPortal.quizPortal.model.dto.LoginSignupResponse;
import com.quizPortal.quizPortal.model.dto.UpdateUserRequest;
import com.quizPortal.quizPortal.model.dto.UpdateUserResponse;

public interface UserService {

    LoginSignupResponse createUser(CreateUserRequest request);

    UpdateUserResponse getUser(String token);

    UpdateUserResponse updateUser(UpdateUserRequest request, String token);

    LoginSignupResponse userLogin(CreateUserRequest request);

   // void userLogout(String token);
}
