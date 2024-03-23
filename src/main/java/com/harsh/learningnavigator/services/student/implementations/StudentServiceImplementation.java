package com.harsh.learningnavigator.services.student.implementations;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.harsh.learningnavigator.dto.EmptyBodyDto;
import com.harsh.learningnavigator.dto.StudentDto;
import com.harsh.learningnavigator.dto.StudentReqeustDto;
import com.harsh.learningnavigator.entity.Student;
import com.harsh.learningnavigator.entity.Subject;
import com.harsh.learningnavigator.exception.ResourceNotFoundException;
import com.harsh.learningnavigator.repository.StudentRepository;
import com.harsh.learningnavigator.repository.SubjectRepository;
import com.harsh.learningnavigator.services.student.StudentService;

@Service
public class StudentServiceImplementation implements StudentService {

    private StudentRepository studentRepository;
    private SubjectRepository subjectRepository;
    private ModelMapper modelMapper;

    public StudentServiceImplementation(StudentRepository studentRepository, SubjectRepository subjectRepository,
            ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDto addStudent(StudentReqeustDto entity) {
        Student newStudent = new Student();
        newStudent.setName(entity.getName());
        Student savedStudent = studentRepository.save(newStudent);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student", "Student Id", Long.toString(id)));
        return modelMapper.map(student, StudentDto.class);        
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        students.forEach(exam -> {
            studentDtos.add(
                modelMapper.map(exam, StudentDto.class)
            );
        });
        return studentDtos;
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto studentDto) {
        if(studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }

        if(studentDto == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student", "Student Id", Long.toString(studentId)));

        student.setName(studentDto.getName());
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public StudentDto enrollStudentToSubject(Long studentId, Long subjectId) {
        if(studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        if(subjectId == null) {
            throw new IllegalArgumentException("Subject ID cannot be null");
        }
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student", "Student Id", Long.toString(studentId)));
        Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new ResourceNotFoundException("Subject", "Subject Id", Long.toString(subjectId)));
        
        student.addSubject(subject);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public EmptyBodyDto delete(Long studentId) {
        if(studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student", "Student Id", Long.toString(studentId)));
        studentRepository.delete(student);
        return new EmptyBodyDto();
    }
    
}
