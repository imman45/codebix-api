package com.codebix.teacherservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Teachers {
	
	@Id
	private Integer rollno;
	private String name;
	private String password;

}
