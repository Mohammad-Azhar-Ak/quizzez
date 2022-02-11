package com.quizPortal.quizPortal.dao;

import com.quizPortal.quizPortal.model.Entities.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionDao extends JpaRepository<UserSession, Integer> {

        UserSession findByToken(String token);
}
