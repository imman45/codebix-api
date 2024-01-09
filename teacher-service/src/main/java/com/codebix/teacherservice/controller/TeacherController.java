package com.codebix.teacherservice.controller;

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


import com.codebix.teacherservice.model.VerifyData;
import com.codebix.teacherservice.model.Questions;
import com.codebix.teacherservice.model.QuizDto;
import com.codebix.teacherservice.model.Teachers;
import com.codebix.teacherservice.service.TeacherService;

@RestController
@RequestMapping("teacher")
@CrossOrigin(origins = "*")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@PostMapping("signup")
	public ResponseEntity<String> signupTeacher(@RequestBody Teachers teacherModel){
		return teacherService.signupTeacher(teacherModel);
	}
	
	@PostMapping("login")
	public ResponseEntity<String> loginTeacher(@RequestBody VerifyData verifydata){
		return teacherService.loginTeacher(verifydata);
	}
	
	@GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category){
		return teacherService.getQuestionsByCategory(category);
	}
	
	@PostMapping("addquestion")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question){
		return teacherService.addQuestion(question);
	}
	
	@PostMapping("createquiz")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
		return teacherService.createQuiz(quizDto);
	}
	

}
