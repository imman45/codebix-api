package com.codebix.studentservice.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Students {
	@Id
	//@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer rollno;
	private String username;
	private String password;
	

}
