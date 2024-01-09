package com.codebix.questionservice.service;


import com.codebix.questionservice.dao.QuestionDao;
import com.codebix.questionservice.model.Questions;
import com.codebix.questionservice.model.Response;
import com.codebix.questionservice.model.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

 
    public ResponseEntity<List<Questions>> getQuestionsByCategory(String cat) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(cat),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Questions question) {
        questionDao.save(question);
        return new ResponseEntity<>("\"success\"",HttpStatus.CREATED);
    }
    
    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right = 0;
        for (Response response : responses) {
            Questions question = questionDao.findById(response.getId()).get();
            if (response.getAnswer().equals(question.getCorrectAnswer()))
                right++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }

    
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
    	
        List<QuestionWrapper> wrapperList = new ArrayList<>();
        List<Questions> questions = new ArrayList<>();
        
        for(Integer id : questionIds){
            questions.add(questionDao.findById(id).get());
        }

        for(Questions question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionText(question.getQuestionText());
            wrapper.setOption_A(question.getOption_A());
            wrapper.setOption_B(question.getOption_B());
            wrapper.setOption_C(question.getOption_C());
            wrapper.setOption_D(question.getOption_D());
            wrapperList.add(wrapper);
        }
        return new ResponseEntity<>(wrapperList, HttpStatus.OK);
    }
    
    
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questionsids = questionDao.findRandomQuestionsByCatagory(categoryName, numQuestions);
        return new ResponseEntity<>(questionsids, HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> practiceQuiz(String categoryName) {
        List<Integer> questionsids = questionDao.findRandomQuestionsByCatagory(categoryName);
        return new ResponseEntity<>(questionsids, HttpStatus.OK);
    }
}
