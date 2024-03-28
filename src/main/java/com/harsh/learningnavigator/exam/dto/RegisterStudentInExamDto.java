package com.harsh.learningnavigator.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a request to register a student for an exam.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterStudentInExamDto {

    /** The ID of the student to register for the exam. */
    private Long studentId;
    
}
