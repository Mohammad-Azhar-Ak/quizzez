package com.quizPortal.quizPortal.dao;

import com.quizPortal.quizPortal.model.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {


    List<Question> findAllByQuizId(int quizId);
}
