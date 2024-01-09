package com.codebix.studentservice.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.codebix.studentservice.dao.StudentDAO;
import com.codebix.studentservice.feign.QuestionInterface;
import com.codebix.studentservice.feign.QuizInterface;
import com.codebix.studentservice.model.QuestionWrapper;
import com.codebix.studentservice.model.Questions;
import com.codebix.studentservice.model.Response;
import com.codebix.studentservice.model.Students;
import com.codebix.studentservice.model.VerifyData;

@Service
public class StudentService {
	
	@Autowired
	StudentDAO studentDAO;
	
	@Autowired
	QuestionInterface questionInterface;
	
	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> addStudent(Students studentModel) {
		
		if(studentDAO.findById(studentModel.getRollno()).isEmpty()) {
			studentDAO.save(studentModel);
			return new ResponseEntity<>("\"student added\"",HttpStatus.CREATED);
		}
		return new ResponseEntity<>("\"already student exist\"",HttpStatus.ALREADY_REPORTED);
	}
	
	public ResponseEntity<String> verifyStudent(VerifyData verifydata){
		
		if(studentDAO.findById(verifydata.getRollno()).isPresent()) {
			
			if(studentDAO.findById(verifydata.getRollno()).get().getPassword().equals(verifydata.getPassword()) ){
				return new ResponseEntity<>("\"authorized\"",HttpStatus.OK);
			}
			return new ResponseEntity<>("\"password not matched\"",HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>("\"No user\"",HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category){
		return questionInterface.getQuestionsByCategory(category);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions() {
		return quizInterface.getQuizQuestions() ;
	}

	public ResponseEntity<Integer> submitQuiz(List<Response> responses) {
		return quizInterface.submitQuiz(responses);
	}

}
