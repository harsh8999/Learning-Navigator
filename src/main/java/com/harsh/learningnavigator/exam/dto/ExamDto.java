package com.harsh.learningnavigator.exam.dto;

import java.util.Set;

import com.harsh.learningnavigator.student.entity.Student;
import com.harsh.learningnavigator.subject.entity.Subject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamDto {
    
    Long id;
    Subject subject;
    Set<Student> registeredStudents;
}
