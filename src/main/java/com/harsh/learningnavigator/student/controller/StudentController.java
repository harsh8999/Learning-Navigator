package com.harsh.learningnavigator.student.controller;

import org.springframework.web.bind.annotation.RestController;

import com.harsh.learningnavigator.student.dto.StudentDto;
import com.harsh.learningnavigator.student.dto.StudentRequestDto;
import com.harsh.learningnavigator.student.services.StudentService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class StudentController {

    private static final String URL_PATH = "/students";
    
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Adds a new student.
     * 
     * @param entity The request DTO containing information about the student to be added.
     * @return ResponseEntity with the added student DTO and HTTP status CREATED.
     */
    @PostMapping(URL_PATH)
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentRequestDto entity) {
        return new ResponseEntity<>(studentService.addStudent(entity) , HttpStatus.CREATED);
    }

     /**
     * Retrieves all students.
     * 
     * @return ResponseEntity with a list of student DTOs and HTTP status OK.
     */
    @GetMapping(URL_PATH)
    public ResponseEntity<List<StudentDto>> getAllExams() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    /**
     * Retrieves a student by ID.
     * 
     * @param studentId The ID of the student to retrieve.
     * @return ResponseEntity with the retrieved student DTO and HTTP status OK.
     */
    @GetMapping(URL_PATH + "/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable("studentId") Long studentId) {
        return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
    }
    
    /**
     * Enrolls a student to a subject.
     * 
     * @param studentId The ID of the student to enroll.
     * @param subjectId The ID of the subject to enroll the student into.
     * @return ResponseEntity with the enrolled student DTO and HTTP status OK.
     */
    @PutMapping(URL_PATH + "/{studentId}/subject/{subjectId}")
    public ResponseEntity<StudentDto> enrollSubject(@PathVariable("studentId") Long studentId, @PathVariable("subjectId") Long subjectId) {
        return new ResponseEntity<>(studentService.enrollStudentToSubject(studentId, subjectId), HttpStatus.OK);
    }

    /**
     * Updates an existing student.
     * 
     * @param studentId The ID of the student to update.
     * @param entity The DTO containing updated information about the student.
     * @return ResponseEntity with the updated student DTO and HTTP status OK.
     */
    @PutMapping(URL_PATH + "/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto entity) {
        return new ResponseEntity<>(studentService.updateStudent(studentId, entity), HttpStatus.OK);
    }
    
    /**
     * Deletes a student.
     * 
     * @param studentId The ID of the student to delete.
     * @return ResponseEntity with HTTP status NO_CONTENT.
     */
    @DeleteMapping(URL_PATH + "/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        // return new ResponseEntity<>(studentService.delete(studentId), HttpStatus.NO_CONTENT);
        studentService.delete(studentId);
        return ResponseEntity.noContent().build();
    }

}
