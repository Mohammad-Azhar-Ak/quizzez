package com.quizPortal.quizPortal.service.impl;

import com.quizPortal.quizPortal.model.Entities.User;
import com.quizPortal.quizPortal.model.Entities.UserSession;
import com.quizPortal.quizPortal.dao.UserSessionDao;
import com.quizPortal.quizPortal.model.dto.CreateUserRequest;
import com.quizPortal.quizPortal.model.dto.LoginSignupResponse;
import com.quizPortal.quizPortal.model.dto.UpdateUserRequest;
import com.quizPortal.quizPortal.model.dto.UpdateUserResponse;
import com.quizPortal.quizPortal.service.UserService;
import com.quizPortal.quizPortal.service.UserSessionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.quizPortal.quizPortal.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserDao userDao;

    @Autowired
    UserSessionService userSessionService;

    @Autowired
    UserSessionDao userSessionDao;

    @Override
    public LoginSignupResponse createUser(CreateUserRequest request) {

        if (StringUtils.isBlank(request.getEmail()))
            throw new IllegalArgumentException("Null value cannot be accepted in email");

        if (StringUtils.isBlank(request.getName()))
            throw new IllegalArgumentException("Invalid Name");

        if (StringUtils.isBlank(request.getPassword()))
            throw new IllegalArgumentException("Invalid Password");

        if (StringUtils.isBlank(request.getMobile()))
            throw new IllegalArgumentException("Invalid Mobile Number");

        if (!request.getEmail().matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"))
            throw new IllegalArgumentException("Invalid Email");

        if (!request.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"))
            throw new IllegalArgumentException("Please enter a valid password having atleast a digit," +
                    " a lowercase character, an uppercase character," +
                    " a special character and should be of minimum length 8 without any space.");

        if (!request.getMobile().matches("\\d{10}"))
            throw new IllegalArgumentException("Invalid Mobile Number, Mobile number must be of 10 digits.");

        if (userDao.findByEmail(request.getEmail()) != null)
            throw new IllegalArgumentException("User with this email already exists.");

        User user = new User();
        BCryptPasswordEncoder encodePassword = new BCryptPasswordEncoder(12);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setPassword(encodePassword.encode(request.getPassword()));

        userDao.save(user);
        UserSession userSession = userSessionService.createSession(user);
        return new LoginSignupResponse(userSession.getToken(), user.getName());
    }

    @Override
    public UpdateUserResponse getUser(String token) {
        if (StringUtils.isBlank(token))
            throw new AccessDeniedException("Unauthorized User");

        UserSession userSession = userSessionDao.findByToken(token);

        if (userSession == null || userSession.getSignOutTime() != null)
            throw new AccessDeniedException("Unauthorized User, signIn Again.");

        User user = userSession.getUser();

        if (user == null)
            throw new IllegalArgumentException("Unauthorized User, signIn Again.");

        return new UpdateUserResponse(user.getName(), user.getGender(),
                user.getLinkedIn(), user.getHobbies(), user.getMobile());
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest request, String token) {
        if (StringUtils.isBlank(token))
            throw new AccessDeniedException("Unauthorized User, Please signIn again.");

        if (StringUtils.isBlank(request.getName()))
            throw new IllegalArgumentException("Name cannot be empty, Please provide valid name.");

        if (StringUtils.isBlank(request.getMobile()))
            throw new IllegalArgumentException("Mobile Number cannot be empty, Please provide 10 digits number.");

        if (!request.getMobile().matches("\\d{10}"))
            throw new IllegalArgumentException("Please enter a valid mobile number.");

//        if (request.getGender() == null)
//            throw new IllegalArgumentException("Gender cannot be null, Please select value" +
//                    "0 for male, 1 for female, 2 for others.");
        UserSession userSession = userSessionDao.findByToken(token);
        if (userSession == null)
            throw new IllegalArgumentException("Unauthorized User, Login Again");//Access denied

        User user = userSession.getUser();
        if (user == null)
            throw new IllegalArgumentException("Invalid user");

        user.setName(request.getName());
        user.setMobile(request.getMobile());
        user.setHobbies(request.getHobbies());
        user.setLinkedIn(request.getLinkedIn());
        user.setGender(request.getGender());
        userDao.save(user);
        return new UpdateUserResponse(user.getName(), user.getGender(),
                user.getLinkedIn(), user.getHobbies(), user.getMobile());
    }

    @Override
    public LoginSignupResponse userLogin(CreateUserRequest request) {
        if (StringUtils.isBlank(request.getEmail()))
            throw new IllegalArgumentException("Please provide valid email.");

        if (StringUtils.isBlank(request.getPassword()))
            throw new IllegalArgumentException("Password cannot be empty.");

        User user = userDao.findByEmail(request.getEmail());
        if (user == null)
            throw new IllegalArgumentException("User Not Registered");
        //email check + password check
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("Invalid Credentials");

        UserSession userSession = userSessionService.createSession(user);
        return new LoginSignupResponse(userSession.getToken(), user.getName());


    }
}
