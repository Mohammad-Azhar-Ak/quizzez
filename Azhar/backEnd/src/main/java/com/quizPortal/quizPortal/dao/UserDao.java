package com.quizPortal.quizPortal.dao;

import com.quizPortal.quizPortal.model.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
