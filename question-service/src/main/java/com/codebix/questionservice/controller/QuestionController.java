package com.codebix.questionservice.controller;


import com.codebix.questionservice.model.Questions;
import com.codebix.questionservice.model.Response;
import com.codebix.questionservice.model.QuestionWrapper;
import com.codebix.questionservice.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;


    @GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question){
        return  questionService.addQuestion(question);
    }


    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsFromId(questionIds);
    }
    
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
    
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions ){
        return questionService.generateQuestionsForQuiz(categoryName, numQuestions);
    }
    
    @GetMapping("practicequiz")
    public ResponseEntity<List<Integer>> practiceQuiz
            (@RequestParam String categoryName){
        return questionService.practiceQuiz(categoryName);
    }

   


    // generate
    // getQuestions (questionid)
    // getScore

}
