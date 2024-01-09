package com.codebix.studentservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.codebix.studentservice.model.Students;

@Repository
public interface StudentDAO extends JpaRepository<Students, Integer> {

}
