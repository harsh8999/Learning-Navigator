package com.harsh.learningnavigator.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.learningnavigator.exam.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    
}
