package com.harsh.learningnavigator.exam.dto;

import java.util.Set;

import com.harsh.learningnavigator.student.entity.Student;
import com.harsh.learningnavigator.subject.entity.Subject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Data Transfer Object (DTO) representing an exam.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamDto {
    
    /** Unique identifier for the exam DTO. */
    Long id;

    /** The subject of the exam. */
    Subject subject;

    /** 
     * Set of students registered for the exam.
     * This field represents a collection of Student entities.
     */
    Set<Student> registeredStudents;
}
