package com.harsh.learningnavigator.student.dto;

import java.util.Set;

import com.harsh.learningnavigator.exam.entity.Exam;
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
public class StudentDto {
    Long id;
    String name;
    Set<Subject> subjects;
    Set<Exam> exams;
}
