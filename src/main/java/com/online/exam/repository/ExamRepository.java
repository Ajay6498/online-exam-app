package com.online.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.exam.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
	
}