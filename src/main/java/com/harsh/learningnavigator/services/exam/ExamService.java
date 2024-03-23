package com.harsh.learningnavigator.services.exam;

import java.util.List;

import com.harsh.learningnavigator.dto.ExamDto;
import com.harsh.learningnavigator.dto.RegisterStudentInExamDto;
import com.harsh.learningnavigator.entity.Exam;

public interface ExamService {

    List<ExamDto> getExams();
    ExamDto getExamById(Long examId);
    ExamDto addExam(Long subjectId);
    Exam registerStudent(Long examId, RegisterStudentInExamDto student);
}
