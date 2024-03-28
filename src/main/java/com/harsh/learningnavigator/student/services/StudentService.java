package com.harsh.learningnavigator.student.services;

import java.util.List;

import com.harsh.learningnavigator.dto.EmptyBodyDto;
import com.harsh.learningnavigator.student.dto.StudentDto;
import com.harsh.learningnavigator.student.dto.StudentRequestDto;

/**
 * Service interface for managing students.
 */
public interface StudentService {
    /**
     * Adds a new student.
     * 
     * @param entity The request DTO containing information about the student to be added.
     * @return The DTO representing the added student.
     */
    StudentDto addStudent(StudentRequestDto entity);

    /**
     * Retrieves a student by ID.
     * 
     * @param id The ID of the student to retrieve.
     * @return The DTO representing the retrieved student.
     */
    StudentDto getStudentById(Long id);

    /**
     * Retrieves all students.
     * 
     * @return A list of DTOs representing all students.
     */
    List<StudentDto> getAllStudents();

    /**
     * Updates an existing student.
     * 
     * @param studentId The ID of the student to update.
     * @param studentDto The DTO containing updated information about the student.
     * @return The DTO representing the updated student.
     */
    StudentDto updateStudent(Long studentId, StudentDto studentDto);

    /**
     * Enrolls a student to a subject.
     * 
     * @param studentId The ID of the student to enroll.
     * @param subjectId The ID of the subject to enroll the student into.
     * @return The DTO representing the enrolled student.
     */
    StudentDto enrollStudentToSubject(Long studentId, Long subjectId);

    /**
     * Deletes a student.
     * 
     * @param studentId The ID of the student to delete.
     * @return An empty DTO indicating the success of the deletion.
     */
    EmptyBodyDto delete(Long studentId);
}
