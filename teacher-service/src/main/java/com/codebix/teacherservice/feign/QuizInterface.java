package com.codebix.teacherservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.codebix.teacherservice.model.QuizDto;


@FeignClient("QUIZ-SERVICE")
public interface QuizInterface {
	
	@PostMapping("quiz/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto);

}
