package com.harsh.learningnavigator.student.services;

import java.util.List;

import com.harsh.learningnavigator.dto.EmptyBodyDto;
import com.harsh.learningnavigator.student.dto.StudentDto;
import com.harsh.learningnavigator.student.dto.StudentRequestDto;

public interface StudentService {
    StudentDto addStudent(StudentRequestDto entity);
    StudentDto getStudentById(Long id);
    List<StudentDto> getAllStudents();
    StudentDto updateStudent(Long studentId, StudentDto studentDto);
    StudentDto enrollStudentToSubject(Long studentId, Long subjectId);
    EmptyBodyDto delete(Long studentId);
}
