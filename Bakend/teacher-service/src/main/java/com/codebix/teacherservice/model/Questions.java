package com.codebix.teacherservice.model;

import lombok.Data;

@Data
public class Questions {
	private Integer id;
    private String questionText;
    private String option_A;
    private String option_B;
    private String option_C;
    private String option_D;
    private String correctAnswer;
    private String category;
    private String explanation;
}
