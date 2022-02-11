package com.quizPortal.quizPortal.service;
import com.quizPortal.quizPortal.model.Entities.Question;
import com.quizPortal.quizPortal.model.dto.AfterSubmitResponse;
import com.quizPortal.quizPortal.model.dto.CreateAndUpdateQuestionRequest;
import com.quizPortal.quizPortal.model.dto.SubmitQuestionsRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {


    Question addQuestion(CreateAndUpdateQuestionRequest request, Integer quizId);

    List<Question> getAllQuestion(Integer quizId, String token);

    AfterSubmitResponse submitQuiz(List<SubmitQuestionsRequest> list, String token, Integer quizId);
}
