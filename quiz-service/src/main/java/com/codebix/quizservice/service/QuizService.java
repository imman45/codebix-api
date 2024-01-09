package com.codebix.quizservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.codebix.quizservice.dao.QuizDao;
import com.codebix.quizservice.feign.QuestionInterface;
import com.codebix.quizservice.model.QuestionWrapper;
import com.codebix.quizservice.model.Quiz;
import com.codebix.quizservice.model.Response;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
    	quizDao.deleteAll();
        List<Integer> questionsIds = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionsIds);
        quizDao.save(quiz);

        return new ResponseEntity<>("Suuccess", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions() {
//          Quiz quiz = quizDao.findById(id).get();
          Quiz quiz = quizDao.findAll().get(0);
          List<Integer> questionIds = quiz.getQuestionIds();
          ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
          return questions;

    }

    public ResponseEntity<Integer> calculateResult(List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
