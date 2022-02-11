package com.quizPortal.quizPortal.service.impl;

import com.quizPortal.quizPortal.model.Entities.Quiz;
import com.quizPortal.quizPortal.dao.QuestionDao;
import com.quizPortal.quizPortal.dao.QuizDao;
import com.quizPortal.quizPortal.model.Entities.Question;
import com.quizPortal.quizPortal.model.dto.AfterSubmitResponse;
import com.quizPortal.quizPortal.model.dto.CreateAndUpdateQuestionRequest;
import com.quizPortal.quizPortal.model.dto.SubmitQuestionsRequest;
import com.quizPortal.quizPortal.service.QuestionService;
import com.quizPortal.quizPortal.service.UserSessionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.AccessDeniedException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuizDao quizDao;

    @Autowired
    UserSessionService userSessionService;


    @Override
    public Question addQuestion(CreateAndUpdateQuestionRequest request, Integer quizId) {
        Question question= new Question();
        if(StringUtils.isBlank(request.getStatement()))
            throw new IllegalArgumentException("Statement cannot be empty");
        if(request.getAnswer()==null)
            throw new IllegalArgumentException("Answers cannot be null");
//        if(request.getAnswer()!=true && request.getAnswer()!=false)
//            throw new IllegalArgumentException("Answer must be true/false");
        if(quizId==null)
            throw new IllegalArgumentException("Quiz id cannot be null");
        if(request.getMarks()==null)
            throw new IllegalArgumentException("Marks cannot be null");
        if(request.getMarks()<=0)
            throw new IllegalArgumentException("Marks should be greater than 0.");

        question.setStatement(request.getStatement());
        question.setAnswer(request.getAnswer());
        question.setMarks(request.getMarks());
        Quiz quiz = quizDao.getById(quizId);
        question.setQuiz(quiz);
        questionDao.save(question);
        return null;
    }

    @Override
    public List<Question> getAllQuestion(Integer quizId, String token) {
        if(quizId==null)
            throw new IllegalArgumentException("Quiz id cannot be null.");
        if(userSessionService.validateSession(token)==null)
            throw new AccessDeniedException("Access denied, Please signIn again.");
        return questionDao.findAllByQuizId(quizId);
    }

    //Submit Quiz
    @Override
    public AfterSubmitResponse submitQuiz(List<SubmitQuestionsRequest> request, String token, Integer quizId) {
        if(quizId==null)
            throw new IllegalArgumentException("Quiz id cannot be null.");

        if(userSessionService.validateSession(token)==null)
            throw new AccessDeniedException("Access denied");

        List<Question> savedList = questionDao.findAllByQuizId(quizId);
        if(savedList==null)
            throw new IllegalArgumentException("No questions found in this Quiz.");

        //MAP ka use karo

        Integer rightCount=0,wrongCount=0,total_marks=0,marks_scored=0;
        for(Question dataList : savedList){
            for(SubmitQuestionsRequest userList : request ){
                Integer userListId = userList.getId();
                if(userListId == null)
                    throw new IllegalArgumentException("Question id not be null");
                if(userList.getAnswer()==null)
                    continue;
                if(userListId == dataList.getId()) {
                    if (userList.getAnswer().equals(dataList.isAnswer())) {
                        rightCount++;
                        marks_scored += dataList.getMarks();
                        break;
                    }
                    else {
                        wrongCount++;
                        break;
                }

                }
            }
            total_marks += dataList.getMarks();
        }
        AfterSubmitResponse obj = new AfterSubmitResponse(rightCount,wrongCount,marks_scored,total_marks);
        return obj;
    }
}





//        for (SubmitQuestionsRequest listOfAnswer:request){
//            int id=listOfAnswer.getId();
//            Boolean user_ans= listOfAnswer.getAnswer();
//            if(user_ans==null)//ye answer hm quiz me cosider nhi karenge
//            {
//                continue;
//            }
//            Question question=questionDao.getById(id);
//            boolean saved_ans = question.isAnswer();
//            int final_result = Boolean.compare(user_ans,saved_ans);//. equals wala function use kar le..
//            if(final_result==0)//both values are equal
//            {
//                rightCount++;
//                marks_scored+=question.getMarks();
//            }
//            else wrongCount++;
//
//            total_marks+=question.getMarks();
//        }
