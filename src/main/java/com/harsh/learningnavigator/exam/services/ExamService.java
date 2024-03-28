package com.harsh.learningnavigator.exam.services;

import java.util.List;

import com.harsh.learningnavigator.dto.EmptyBodyDto;
import com.harsh.learningnavigator.exam.dto.ExamDto;
import com.harsh.learningnavigator.exam.dto.RegisterStudentInExamDto;

/**
 * Service interface for managing exams.
 */
public interface ExamService {

    /**
     * Retrieves all exams.
     * 
     * @return List of exam DTOs.
     */
    List<ExamDto> getExams();

    /**
     * Retrieves an exam by ID.
     * 
     * @param examId The ID of the exam to retrieve.
     * @return The exam DTO.
     */
    ExamDto getExamById(Long examId);

    /**
     * Adds an exam for the given subject.
     * 
     * @param subjectId The ID of the subject for which the exam is added.
     * @return The added exam DTO.
     */
    ExamDto addExam(Long subjectId);

    /**
     * Registers a student for an exam.
     * 
     * @param examId The ID of the exam to register the student for.
     * @param student The DTO containing information about the student to register.
     * @return The updated exam DTO.
     */
    ExamDto registerStudent(Long examId, RegisterStudentInExamDto student);

    /**
     * Deletes an exam.
     * 
     * @param examId The ID of the exam to delete.
     * @return Empty body DTO.
     */
    EmptyBodyDto deleteExam(Long examId);
    
}
