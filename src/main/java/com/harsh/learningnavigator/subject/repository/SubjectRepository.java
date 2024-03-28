package com.harsh.learningnavigator.subject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.learningnavigator.subject.entity.Subject;

/**
 * Repository interface for managing subjects in the database.
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
}
