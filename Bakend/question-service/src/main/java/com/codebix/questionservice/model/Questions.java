package com.codebix.questionservice.model;

import jakarta.persistence.Entity;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
