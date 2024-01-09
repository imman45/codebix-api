package com.codebix.studentservice.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.codebix.studentservice.model.Questions;


@FeignClient("QUESTION-SERVICE")
public interface QuestionInterface {
		
	@GetMapping("question/category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category);
	
	@PostMapping("question/add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question);
	
}
