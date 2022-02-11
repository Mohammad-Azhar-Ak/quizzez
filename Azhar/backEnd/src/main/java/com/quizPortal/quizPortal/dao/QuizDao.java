package com.quizPortal.quizPortal.dao;

import com.quizPortal.quizPortal.model.Entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {


}
