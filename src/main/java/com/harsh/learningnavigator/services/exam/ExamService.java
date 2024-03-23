package com.harsh.learningnavigator.services.exam;

import java.util.List;

import com.harsh.learningnavigator.dto.ExamDto;
import com.harsh.learningnavigator.dto.RegisterStudentInExamDto;

public interface ExamService {

    List<ExamDto> getExams();
    ExamDto getExamById(Long examId);
    ExamDto addExam(Long subjectId);
    ExamDto registerStudent(Long examId, RegisterStudentInExamDto student);
}
