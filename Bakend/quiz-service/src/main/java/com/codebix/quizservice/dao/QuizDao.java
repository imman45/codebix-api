package com.codebix.quizservice.dao;


import com.codebix.quizservice.model.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
