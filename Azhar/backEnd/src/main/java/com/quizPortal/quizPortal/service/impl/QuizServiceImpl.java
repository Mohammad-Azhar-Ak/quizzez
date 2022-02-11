package com.quizPortal.quizPortal.service.impl;



import com.quizPortal.quizPortal.model.Entities.Quiz;
import com.quizPortal.quizPortal.dao.QuizDao;
import com.quizPortal.quizPortal.model.dto.CreateUpdateQuizRequest;
import com.quizPortal.quizPortal.service.QuizService;
import com.quizPortal.quizPortal.service.UserSessionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    UserSessionService userSessionService;


    @Override
    public Quiz createQuiz(CreateUpdateQuizRequest request) {
        if(StringUtils.isBlank(request.getTitle()))
            throw new IllegalArgumentException("Title cannot be empty.");
        Quiz quiz = new Quiz();
        quiz.setTitle(request.getTitle());
        return quizDao.save(quiz);
    }

    @Override
    public Quiz getQuiz(Integer quizId) {
        if(quizId==null)
            throw new IllegalArgumentException("Quiz id cannot be null");
        Quiz quiz = quizDao.findById(quizId).orElse(null);
        if(quiz==null)
            throw new  IllegalArgumentException("Quiz not found");
        return quiz;
    }

    @Override
    public List<Quiz> getAllQuiz(String token) {


        if(userSessionService.validateSession(token)==null)
            throw new AccessDeniedException("Access denied, Please signIn again");
        return quizDao.findAll();
    }
}
