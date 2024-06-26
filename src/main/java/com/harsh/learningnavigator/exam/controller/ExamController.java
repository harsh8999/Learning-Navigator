package com.harsh.learningnavigator.exam.controller;

import org.springframework.web.bind.annotation.RestController;

import com.harsh.learningnavigator.exam.dto.ExamDto;
import com.harsh.learningnavigator.exam.dto.RegisterStudentInExamDto;
import com.harsh.learningnavigator.exam.services.ExamService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ExamController {

    private static final String URL_PATH = "/exams";
    
    private ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }


    /**
     * Retrieves all exams.
     * 
     * @return ResponseEntity with a list of exam DTOs and HTTP status OK.
     */
    @GetMapping(URL_PATH)
    public ResponseEntity<List<ExamDto>> getAllExams() {
        return new ResponseEntity<>(examService.getExams(), HttpStatus.OK);
    }

    /**
     * Retrieves an exam by ID.
     * 
     * @param examId The ID of the exam to retrieve.
     * @return ResponseEntity with the retrieved exam DTO and HTTP status OK.
     */
    @GetMapping(URL_PATH + "/{examId}")
    public ResponseEntity<ExamDto> getExam(@PathVariable("examId") Long examId) {
        return new ResponseEntity<>(examService.getExamById(examId), HttpStatus.OK);
    }
    
    /**
     * Adds an exam for a given subject.
     * 
     * @param subjectId The ID of the subject for which the exam is added.
     * @return ResponseEntity with the added exam DTO and HTTP status CREATED.
     */
    @PostMapping(URL_PATH + "/subject/{subjectId}")
    public ResponseEntity<ExamDto> addExam(@PathVariable("subjectId") Long subjectId) {
        return new ResponseEntity<>(examService.addExam(subjectId), HttpStatus.CREATED);
    }
    
    /**
     * Registers a student for an exam.
     * 
     * @param examId The ID of the exam for which the student is registered.
     * @param studentInExamDto The DTO containing information about the student registration.
     * @return ResponseEntity with the updated exam DTO and HTTP status OK.
     */
    @PostMapping(URL_PATH + "/{examId}")
    public ResponseEntity<ExamDto> registerStudentInExam(@PathVariable("examId") Long examId, @RequestBody RegisterStudentInExamDto studentInExamDto) {
        return new ResponseEntity<>(examService.registerStudent(examId, studentInExamDto), HttpStatus.OK);
    }
   
    /**
     * Deletes an exam.
     * 
     * @param examId The ID of the exam to delete.
     * @return ResponseEntity with HTTP status NO_CONTENT.
     */
    @DeleteMapping(URL_PATH + "/{examId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long examId) {
        // return new ResponseEntity<>(examService.deleteExam(examId), HttpStatus.NO_CONTENT);
        examService.deleteExam(examId);
        return ResponseEntity.noContent().build();
    }
}
