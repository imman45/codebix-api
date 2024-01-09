package com.codebix.quizservice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codebix.quizservice.model.QuestionWrapper;
import com.codebix.quizservice.model.QuizDto;
import com.codebix.quizservice.model.Response;
import com.codebix.quizservice.service.QuizService;

import java.util.List;

@RestController
@RequestMapping("quiz")
@CrossOrigin(origins = "*")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
    	System.out.println("Hello quiz controller");
    	System.out.println(quizDto.getNumQuestions());
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
    }

    @GetMapping("getquiz")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(){
        return quizService.getQuizQuestions();
    }

    @PostMapping("submitquiz")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> responses){
        return quizService.calculateResult(responses);
    }


}
