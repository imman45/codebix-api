package com.codebix.teacherservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.codebix.teacherservice.dao.TeacherDAO;
import com.codebix.teacherservice.feign.QuestionInterface;
import com.codebix.teacherservice.feign.QuizInterface;
import com.codebix.teacherservice.model.Questions;
import com.codebix.teacherservice.model.QuizDto;
import com.codebix.teacherservice.model.Teachers;
import com.codebix.teacherservice.model.VerifyData;

@Service
public class TeacherService {
	
	@Autowired
	TeacherDAO teacherDAO;
	
	@Autowired
	QuestionInterface questionInterface;
	
	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> signupTeacher(Teachers teacherModel) {
		
		if(teacherDAO.findById(teacherModel.getRollno()).isEmpty()) {
			teacherDAO.save(teacherModel);
			return new ResponseEntity<>("\"successfully teacher added\"",HttpStatus.CREATED);
		}
		return new ResponseEntity<>("\"unsuccess already teacher exist\"",HttpStatus.ALREADY_REPORTED);
	}

	public ResponseEntity<String> loginTeacher(VerifyData verifydata) {
		
		if(teacherDAO.findById(verifydata.getRollno()).isPresent()) {
			
			if(teacherDAO.findById(verifydata.getRollno()).get().getPassword().equals(verifydata.getPassword()) ){
				return new ResponseEntity<>("\"authorized\"",HttpStatus.OK);
			}
			return new ResponseEntity<>("\"password not matched\"",HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>("\"No user please signup\"",HttpStatus.NO_CONTENT);
		
	}

	public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
		return questionInterface.getQuestionsByCategory(category);
	}

	public ResponseEntity<String> addQuestion(Questions question) {
		return questionInterface.addQuestion(question);
	}

	public ResponseEntity<String> createQuiz(QuizDto quizDto) {
	return quizInterface.createQuiz(quizDto);
	}

	

}
