package com.harsh.learningnavigator.dto;

import java.util.Set;

import com.harsh.learningnavigator.entity.Student;
import com.harsh.learningnavigator.entity.Subject;

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
