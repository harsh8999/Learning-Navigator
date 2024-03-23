package com.harsh.learningnavigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.learningnavigator.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    
}
