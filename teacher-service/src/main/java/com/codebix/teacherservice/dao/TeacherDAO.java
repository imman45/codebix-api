package com.codebix.teacherservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codebix.teacherservice.model.Teachers;

@Repository
public interface TeacherDAO extends JpaRepository<Teachers, Integer>{

}
