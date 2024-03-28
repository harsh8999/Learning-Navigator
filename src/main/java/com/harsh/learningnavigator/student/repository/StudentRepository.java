package com.harsh.learningnavigator.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.learningnavigator.student.entity.Student;

/**
 * Repository interface for managing students in the database.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
