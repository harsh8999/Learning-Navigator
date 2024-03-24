package com.harsh.learningnavigator.exam.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;

import com.harsh.learningnavigator.exam.dto.RegisterStudentInExamDto;
import com.harsh.learningnavigator.exam.entity.Exam;
import com.harsh.learningnavigator.exam.repository.ExamRepository;
import com.harsh.learningnavigator.exam.services.implementations.ExamServiceImplementation;
import com.harsh.learningnavigator.exception.exceptions.ResourceNotFoundException;
import com.harsh.learningnavigator.student.entity.Student;
import com.harsh.learningnavigator.student.repository.StudentRepository;
import com.harsh.learningnavigator.subject.repository.SubjectRepository;

@SpringBootTest
public class ExamServiceTest {
    
    @InjectMocks
    private ExamServiceImplementation examService;

    @MockBean
    private ExamRepository examRepository;
    
    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private SubjectRepository subjectRepository;

    @BeforeEach
    public void setUp() {
        examService = new ExamServiceImplementation(examRepository, subjectRepository, studentRepository);
    }

    @Test
    @Description("IllegalArgumentException Test")
    public void getExamById_IllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> examService.getExamById(null));
    }

    @Test
    @Description("ResourceNotFoundException Test")
    public void getExamById_ResourceNotFoundExceptionTest() {
        when(examRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> examService.getExamById(1L));
    }

    @Test
    @Description("IllegalArgumentException Test")
    public void addExam_IllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> examService.addExam(null));
    }

    @Test
    @Description("ResourceNotFoundException Test when Subject is not found")
    public void addExam_ResourceNotFoundExceptionTest() {
        when(subjectRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> examService.addExam(1L));
    }

    @Test
    @Description("IllegalArgumentException Test for all values")
    public void registerStudent_IllegalArgumentExceptionTest() { 
        // when both values are null
        assertThrows(IllegalArgumentException.class, () -> examService.registerStudent(null, null));
        // when entity values is null
        assertThrows(IllegalArgumentException.class, () -> examService.registerStudent(1L, null));
        // when examId values is null
        assertThrows(IllegalArgumentException.class, () -> examService.registerStudent(null, new RegisterStudentInExamDto()));
        // when student ID values is null
        assertThrows(IllegalArgumentException.class, () -> examService.registerStudent(null, new RegisterStudentInExamDto(null)));
        // when student ID values is null
        assertThrows(IllegalArgumentException.class, () -> examService.registerStudent(null, new RegisterStudentInExamDto()));
    }

    @Test
    @Description("IllegalStateException Test when student is not enrolled in a subject")
    public void registerStudent_IllegalStateExceptionTest() { 
        Student student = new Student(1L, "Harsh", new HashSet<>(), new HashSet<>());
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(examRepository.findById(anyLong())).thenReturn(Optional.of(new Exam()));
        assertThrows(IllegalStateException.class, () -> examService.registerStudent(1L, new RegisterStudentInExamDto(student.getRegistrationId())));
        
    }

    @Test
    @Description("ResourceNotFoundException Test when")
    public void registerStudent_ResourceNotFoundException_ExamTest() {
        when(examRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> examService.registerStudent(1L, new RegisterStudentInExamDto(1L)));
    }

    @Test
    @Description("ResourceNotFoundException Test when")
    public void registerStudent_ResourceNotFoundException_StudentTest() { 
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(examRepository.findById(anyLong())).thenReturn(Optional.of(new Exam()));
        assertThrows(ResourceNotFoundException.class, () -> examService.registerStudent(1L, new RegisterStudentInExamDto(1L)));
        
    }

    @Test
    @Description("IllegalArgumentException Test")
    public void delete_IllegalArgumentExceptionTest() { 
        assertThrows(IllegalArgumentException.class, () -> examService.deleteExam(null));
    }

    @Test
    @Description("ResourceNotFoundException Test")
    public void delete_ResourceNotFoundExceptionTest() { 
        when(examRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> examService.deleteExam(1L));
    }

}
