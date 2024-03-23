package com.harsh.learningnavigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.learningnavigator.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
