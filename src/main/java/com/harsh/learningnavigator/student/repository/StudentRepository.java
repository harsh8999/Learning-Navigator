package com.harsh.learningnavigator.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.learningnavigator.student.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
