package com.harsh.learningnavigator.exam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harsh.learningnavigator.dto.EmptyBodyDto;
import com.harsh.learningnavigator.exam.dto.ExamDto;
import com.harsh.learningnavigator.exam.dto.RegisterStudentInExamDto;
import com.harsh.learningnavigator.exam.services.implementations.ExamServiceImplementation;
import com.harsh.learningnavigator.exception.handler.GlobalExceptionHandler;
import com.harsh.learningnavigator.subject.entity.Subject;

@WebMvcTest(controllers = ExamController.class)
public class ExamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ExamController examController;

    @MockBean
    private ExamServiceImplementation examService;

    @BeforeEach
    public void setUp() {
        examService = mock(ExamServiceImplementation.class);
        examController = new ExamController(examService);
    }


    private static final String BASE_URL = "/exams";

    @Test
    public void addStudentTest() throws Exception {
        Subject subject = new Subject(1L, "Maths");
        ExamDto examDto = new ExamDto(1L, subject, new HashSet<>());
        
        when(examService.addExam(anyLong())).thenReturn(examDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post(BASE_URL + "/subject/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void getExamsTest() throws Exception {
        // for now just return empty list
        when(examService.getExams()).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders
                .get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

    @Test
    public void getExamByIdTest() throws Exception {
        ExamDto examDto = new ExamDto(1L, new Subject(), null);
        when(examService.getExamById(anyLong())).thenReturn(examDto);

        mockMvc.perform(MockMvcRequestBuilders
                .get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

    @Test
    @Description("IllegalArgumentException Exception")
    public void getStudentById_IllegalArgumentExceptionTest() throws Exception {
        
        when(examService.registerStudent(anyLong(), any(RegisterStudentInExamDto.class))).thenThrow(IllegalArgumentException.class);

        mockMvc = MockMvcBuilders.standaloneSetup(examController)
                .setControllerAdvice(new GlobalExceptionHandler()) // add global exception handler
                .build();

        RegisterStudentInExamDto registerStudentInExamDto = new RegisterStudentInExamDto(1L);
        
        
        mockMvc.perform(MockMvcRequestBuilders
                .post(BASE_URL + "/9")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(registerStudentInExamDto)))
            .andExpect(status().isNotFound()); // Assert HTTP status code
    }


    @Test
    @Description("Delete Test No content status")
    public void deleteStudentTest() throws Exception {
        
        when(examService.deleteExam(anyLong())).thenReturn(new EmptyBodyDto());

        mockMvc.perform(MockMvcRequestBuilders
                .delete(BASE_URL + "/9")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent()); // Assert HTTP status code
    }

    // Utility method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
