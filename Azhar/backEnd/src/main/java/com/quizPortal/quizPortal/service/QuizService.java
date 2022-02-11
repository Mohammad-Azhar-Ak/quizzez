package com.quizPortal.quizPortal.service;

import com.quizPortal.quizPortal.model.Entities.Quiz;
import com.quizPortal.quizPortal.model.dto.CreateUpdateQuizRequest;

import java.util.List;

public interface QuizService {
    Quiz createQuiz(CreateUpdateQuizRequest request);
    Quiz getQuiz(Integer quizId);
    List<Quiz> getAllQuiz(String token);
}
