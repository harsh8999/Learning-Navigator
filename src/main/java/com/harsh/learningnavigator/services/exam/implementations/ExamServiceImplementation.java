package com.harsh.learningnavigator.services.exam.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.harsh.learningnavigator.dto.ExamDto;
import com.harsh.learningnavigator.dto.RegisterStudentInExamDto;
import com.harsh.learningnavigator.dto.StudentDto;
import com.harsh.learningnavigator.dto.SubjectDto;
import com.harsh.learningnavigator.entity.Exam;
import com.harsh.learningnavigator.entity.Student;
import com.harsh.learningnavigator.entity.Subject;
import com.harsh.learningnavigator.exception.ResourceNotFoundException;
import com.harsh.learningnavigator.repository.ExamRepository;
import com.harsh.learningnavigator.repository.StudentRepository;
import com.harsh.learningnavigator.repository.SubjectRepository;
import com.harsh.learningnavigator.services.exam.ExamService;

@Service
public class ExamServiceImplementation implements ExamService {

    private ExamRepository examRepository;
    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public ExamServiceImplementation(ExamRepository examRepository, SubjectRepository subjectRepository,
            StudentRepository studentRepository, ModelMapper modelMapper) {
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ExamDto> getExams() {
        List<Exam> exams = examRepository.findAll();
        List<ExamDto> examDtos = new ArrayList<>();
        exams.forEach(exam -> {
            examDtos.add(
                // modelMapper.map(exam, ExamDto.class)
                map(exam)
            );
        });
        return examDtos;
    }

    @Override
    public ExamDto getExamById(Long examId) {
        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new ResourceNotFoundException("Exam", "Exam Id", Long.toString(examId)));
        // return modelMapper.map(exam, ExamDto.class);
        return map(exam);
    }

    @Override
    public ExamDto addExam(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new ResourceNotFoundException("Subject", "Subject Id", Long.toString(subjectId)));
        Exam exam = new Exam();
        exam.setSubject(subject);
        Exam savedExam = examRepository.save(exam);
        // return modelMapper.map(savedExam, ExamDto.class);
        return map(exam);

    }

    @Override
    public ExamDto registerStudent(Long examId, RegisterStudentInExamDto entity) {
        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new ResourceNotFoundException("Exam", "Exam Id", Long.toString(examId)));
        Student student = studentRepository.findById(entity.getStudentId())
            .orElseThrow(() -> new ResourceNotFoundException("Student", "Student Id", Long.toString(entity.getStudentId())));
        
        // Check if the student is enrolled in the subject of the exam
        if (!student.getEnrolledSubjects().contains(exam.getSubject())) {
            throw new IllegalStateException("Student is not enrolled in the subject of this exam.");
        }

        // here hibernate is giving error to bidirectional relation 
        // exam.getRegisteredStudents().add(student2);
        // Exam updatedExam = examRepository.save(exam);

        // solution...
        // Add the student to the enrolled students of the exam
        exam.registerStudent(student);

        // Add the exam to the enrolled exams of the student
        student.getRegisteredExams().add(exam);

        // Save both entities to maintain the bidirectional relationship
        examRepository.save(exam);
        studentRepository.save(student);
        return map(exam);
        // return modelMapper.map(exam, ExamDto.class);
    }

    private ExamDto map(Exam exam) {
        ExamDto examDto = new ExamDto();
        examDto.setId(exam.getId());

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(exam.getSubject().getSubjectId());
        subjectDto.setSubjectName(exam.getSubject().getName());
        // examDto.setSubject(subjectDto);
        // Set<StudentDto> studentDtos = new HashSet<>();
        // exam.getRegisteredStudents().forEach(student -> {
        //     StudentDto studentDto = new StudentDto();
        //     studentDto.setId(student.getRegistrationId());
        //     studentDto.setName(student.getName());
        //     studentDtos.add(studentDto);
        // });
        examDto.setRegisteredStudents(exam.getRegisteredStudents());
        return examDto;
    }
        
}
