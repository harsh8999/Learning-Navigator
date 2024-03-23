package com.harsh.learningnavigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.learningnavigator.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
}
