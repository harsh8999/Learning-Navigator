package com.harsh.learningnavigator.controller;

import org.springframework.web.bind.annotation.RestController;

import com.harsh.learningnavigator.dto.EmptyBodyDto;
import com.harsh.learningnavigator.dto.ExamDto;
import com.harsh.learningnavigator.dto.RegisterStudentInExamDto;
import com.harsh.learningnavigator.services.exam.ExamService;

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


    @GetMapping(URL_PATH)
    public ResponseEntity<List<ExamDto>> getAllExams() {
        return new ResponseEntity<>(examService.getExams(), HttpStatus.OK);
    }

    @GetMapping(URL_PATH + "/{examId}")
    public ResponseEntity<ExamDto> getExam(@PathVariable("examId") Long examId) {
        return new ResponseEntity<>(examService.getExamById(examId), HttpStatus.OK);
    }
    
    @PostMapping(URL_PATH + "/subject/{subjectId}")
    public ResponseEntity<ExamDto> addExam(@PathVariable("subjectId") Long subjectId) {
        return new ResponseEntity<>(examService.addExam(subjectId), HttpStatus.CREATED);
    }
    
    @PostMapping(URL_PATH + "/{examId}")
    public ResponseEntity<ExamDto> registerStudentInExam(@PathVariable("examId") Long examId, @RequestBody RegisterStudentInExamDto studentInExamDto) {
        return new ResponseEntity<>(examService.registerStudent(examId, studentInExamDto), HttpStatus.OK);
    }
   
    @DeleteMapping(URL_PATH + "/{examId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long examId) {
        // return new ResponseEntity<>(examService.deleteExam(examId), HttpStatus.NO_CONTENT);
        examService.deleteExam(examId);
        return ResponseEntity.noContent().build();
    }
}
