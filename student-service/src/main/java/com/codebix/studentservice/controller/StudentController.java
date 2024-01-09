package com.codebix.studentservice.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebix.studentservice.model.QuestionWrapper;
import com.codebix.studentservice.model.Questions;
import com.codebix.studentservice.model.Response;
import com.codebix.studentservice.model.Students;
import com.codebix.studentservice.model.VerifyData;
import com.codebix.studentservice.service.StudentService;


@RestController
@RequestMapping("student")
@CrossOrigin(origins = "*")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	
	@PostMapping("signup")
	public ResponseEntity<String> addStudent(@RequestBody Students studentModel){
		return studentService.addStudent(studentModel);
	}
	
	@PostMapping("login")
	public ResponseEntity<String> verifyStudent(@RequestBody VerifyData verifydata){
		return studentService.verifyStudent(verifydata);
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category){
		return studentService.getQuestionsByCategory(category);
	}
	
	@GetMapping("getquiz")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(){
		return studentService.getQuizQuestions();
	}

    @PostMapping("submitquiz")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> responses){
    	return studentService.submitQuiz(responses);
    }
	

}
