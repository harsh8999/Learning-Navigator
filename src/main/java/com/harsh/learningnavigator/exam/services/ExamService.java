package com.harsh.learningnavigator.exam.services;

import java.util.List;

import com.harsh.learningnavigator.dto.EmptyBodyDto;
import com.harsh.learningnavigator.exam.dto.ExamDto;
import com.harsh.learningnavigator.exam.dto.RegisterStudentInExamDto;

public interface ExamService {

    List<ExamDto> getExams();
    ExamDto getExamById(Long examId);
    ExamDto addExam(Long subjectId);
    ExamDto registerStudent(Long examId, RegisterStudentInExamDto student);
    EmptyBodyDto deleteExam(Long examId);
}
