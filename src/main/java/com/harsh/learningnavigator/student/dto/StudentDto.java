package com.harsh.learningnavigator.student.dto;

import java.util.Set;

import com.harsh.learningnavigator.exam.entity.Exam;
import com.harsh.learningnavigator.subject.entity.Subject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Data Transfer Object (DTO) representing a student.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDto {
    
    /** Unique identifier for the student DTO. */
    Long id;
    
    /** The name of the student DTO. */
    String name;
    
    /** 
     * Set of subjects in which the student is enrolled.
     * This field represents a collection of Subject entities.
     */
    Set<Subject> subjects;
    
    /** 
     * Set of exams for which the student is registered.
     * This field represents a collection of Exam entities.
     */
    Set<Exam> exams;

}
