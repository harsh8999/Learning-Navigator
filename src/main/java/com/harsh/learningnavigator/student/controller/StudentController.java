package com.harsh.learningnavigator.student.controller;

import org.springframework.web.bind.annotation.RestController;

import com.harsh.learningnavigator.student.dto.StudentDto;
import com.harsh.learningnavigator.student.dto.StudentReqeustDto;
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

    @PostMapping(URL_PATH)
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentReqeustDto entity) {
        return new ResponseEntity<>(studentService.addStudent(entity) , HttpStatus.CREATED);
    }

    @GetMapping(URL_PATH)
    public ResponseEntity<List<StudentDto>> getAllExams() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping(URL_PATH + "/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable("studentId") Long studentId) {
        return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
    }
    
    @PutMapping(URL_PATH + "/{studentId}/subject/{subjectId}")
    public ResponseEntity<StudentDto> enrollSubject(@PathVariable("studentId") Long studentId, @PathVariable("subjectId") Long subjectId) {
        return new ResponseEntity<>(studentService.enrollStudentToSubject(studentId, subjectId), HttpStatus.OK);
    }

    @PutMapping(URL_PATH + "/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto entity) {
        return new ResponseEntity<>(studentService.updateStudent(studentId, entity), HttpStatus.OK);
    }
    
    @DeleteMapping(URL_PATH + "/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        // return new ResponseEntity<>(studentService.delete(studentId), HttpStatus.NO_CONTENT);
        studentService.delete(studentId);
        return ResponseEntity.noContent().build();
    }

}
