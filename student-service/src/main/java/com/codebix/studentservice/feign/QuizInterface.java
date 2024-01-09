package com.codebix.studentservice.feign;

import java.util.List;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.codebix.studentservice.model.QuestionWrapper;
import com.codebix.studentservice.model.Response;


@FeignClient("QUIZ-SERVICE")
public interface QuizInterface {
	
	@GetMapping("quiz/getquiz")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions();

    @PostMapping("quiz/submitquiz")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> responses);
}
