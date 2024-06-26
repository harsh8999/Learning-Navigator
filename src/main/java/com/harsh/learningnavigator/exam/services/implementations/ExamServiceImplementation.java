package com.harsh.learningnavigator.exam.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.harsh.learningnavigator.dto.EmptyBodyDto;
import com.harsh.learningnavigator.exam.dto.ExamDto;
import com.harsh.learningnavigator.exam.dto.RegisterStudentInExamDto;
import com.harsh.learningnavigator.exam.entity.Exam;
import com.harsh.learningnavigator.exam.repository.ExamRepository;
import com.harsh.learningnavigator.exam.services.ExamService;
import com.harsh.learningnavigator.exception.exceptions.ResourceNotFoundException;
import com.harsh.learningnavigator.student.entity.Student;
import com.harsh.learningnavigator.student.repository.StudentRepository;
import com.harsh.learningnavigator.subject.entity.Subject;
import com.harsh.learningnavigator.subject.repository.SubjectRepository;

/**
 * Implementation of the ExamService interface.
 */
@Service
public class ExamServiceImplementation implements ExamService {

    private ExamRepository examRepository;
    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;

    public ExamServiceImplementation(ExamRepository examRepository, SubjectRepository subjectRepository,
            StudentRepository studentRepository) {
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<ExamDto> getExams() {
        List<Exam> exams = examRepository.findAll();
        List<ExamDto> examDtos = new ArrayList<>();
        exams.forEach(exam -> {
            examDtos.add(
                map(exam)
            );
        });
        return examDtos;
    }

    @Override
    public ExamDto getExamById(Long examId) {
        if(examId == null) {
            throw new IllegalArgumentException("Exam ID cannot be null");
        }
        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new ResourceNotFoundException("Exam", "Exam Id", Long.toString(examId)));
        // return modelMapper.map(exam, ExamDto.class);
        return map(exam);
    }

    @Override
    public ExamDto addExam(Long subjectId) {
        if(subjectId == null) {
            throw new IllegalArgumentException("Subject ID cannot be null");
        }
        Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new ResourceNotFoundException("Subject", "Subject Id", Long.toString(subjectId)));
        Exam exam = new Exam();
        exam.setSubject(subject);
        Exam savedExam = examRepository.save(exam);
        // return modelMapper.map(savedExam, ExamDto.class);
        return map(savedExam);

    }

    @Override
    public ExamDto registerStudent(Long examId, RegisterStudentInExamDto entity) {
        if(examId == null) {
            throw new IllegalArgumentException("Exam ID cannot be null");
        }
        if(entity == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        if(entity.getStudentId() == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        
        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new ResourceNotFoundException("Exam", "Exam Id", Long.toString(examId)));
        Student student = studentRepository.findById(entity.getStudentId())
            .orElseThrow(() -> new ResourceNotFoundException("Student", "Student Id", Long.toString(entity.getStudentId())));
        
        // Check if the student is enrolled in the subject of the exam
        if (!student.getEnrolledSubjects().contains(exam.getSubject())) {
            throw new IllegalStateException("Student is not enrolled in the subject of this exam.");
        }

        exam.registerStudent(student);
        examRepository.save(exam);
        return map(exam);
    }

    private ExamDto map(Exam exam) {
        ExamDto examDto = new ExamDto();
        examDto.setId(exam.getId());
        examDto.setSubject(exam.getSubject());
        examDto.setRegisteredStudents(exam.getRegisteredStudents());
        return examDto;
    }

    @Override
    public EmptyBodyDto deleteExam(Long examId) {
        if(examId == null) {
            throw new IllegalArgumentException("Exam ID cannot be null");
        }

        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new ResourceNotFoundException("Exam", "Exam Id", Long.toString(examId)));
        
        examRepository.delete(exam);
        return new EmptyBodyDto();
    }
        
}
