package com.codebix.questionservice.dao;

import com.codebix.questionservice.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {

    List<Questions> findByCategory(String category);
    
    @Query(value = "SELECT q.id FROM questions q Where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionsByCatagory(String category, int numQ);
    
    @Query(value = "SELECT q.id FROM questions q Where q.catagory=:category ORDER BY RAND() LIMIT :15", nativeQuery = true)
    List<Integer> findRandomQuestionsByCatagory(String category);
}
