package com.codebix.questionservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionWrapper {

	private Integer id;
	private String questionText;
    private String option_A;
    private String option_B;
    private String option_C;
    private String option_D;

//    public QuestionWrapper(Integer id, String questionTitle, String option1, String option2, String option3, String option4) {
//        this.id = id;
//        this.questionText = questionTitle;
//        this.option_A = option1;
//        this.option_B = option2;
//        this.option_C = option3;
//        this.option_D = option4;
//    }
}
