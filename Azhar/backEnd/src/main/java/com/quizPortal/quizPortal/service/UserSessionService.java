package com.quizPortal.quizPortal.service;


import com.quizPortal.quizPortal.model.Entities.User;
import com.quizPortal.quizPortal.model.Entities.UserSession;

public interface UserSessionService {

   UserSession createSession(User user);
   UserSession validateSession(String token);
   void userLogout(String token);
}
