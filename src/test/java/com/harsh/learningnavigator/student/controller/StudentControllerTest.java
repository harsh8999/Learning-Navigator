package com.harsh.learningnavigator.student.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.harsh.learningnavigator.exception.exceptions.ResourceNotFoundException;
import com.harsh.learningnavigator.exception.handler.GlobalExceptionHandler;
import com.harsh.learningnavigator.student.dto.StudentDto;
import com.harsh.learningnavigator.student.dto.StudentRequestDto;
import com.harsh.learningnavigator.student.services.implementations.StudentServiceImplementation;


@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private StudentController studentController;

    @MockBean
    private StudentServiceImplementation studentService;

    @BeforeEach
    public void setUp() {
        studentService = mock(StudentServiceImplementation.class);
        studentController = new StudentController(studentService);
    }


    private static final String BASE_URL = "/students";

    @Test
    public void addStudentTest() throws Exception {
        StudentRequestDto studentRequestDto = new StudentRequestDto("Harsh");
        StudentDto studentResponseDto = new StudentDto(1L, "Harsh", null, null);
        
        when(studentService.addStudent(studentRequestDto)).thenReturn(studentResponseDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(studentRequestDto))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void getStudentTest() throws Exception {
        // for now just return empty list
        when(studentService.getAllStudents()).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders
                .get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

    @Test
    public void getStudentByIdTest() throws Exception {
        StudentDto studentDto = new StudentDto(1L, "Harsh", null, null);
        when(studentService.getStudentById(anyLong())).thenReturn(studentDto);



        mockMvc.perform(MockMvcRequestBuilders
                .get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpectAll(
                status().isOk()
            );

    }

    @Test
    @Description("IllegalArgumentException Exception")
    public void getStudentById_IllegalArgumentExceptionTest() throws Exception {
        // Mock the service to throw ResourceNotFoundException when getStudentById is called
        when(studentService.getStudentById(anyLong())).thenThrow(IllegalArgumentException.class);

        mockMvc = MockMvcBuilders.standaloneSetup(studentController)
                .setControllerAdvice(new GlobalExceptionHandler()) // add global exception handler
                .build();
        
        // Perform GET request and assert the response status
        mockMvc.perform(MockMvcRequestBuilders
                .get(BASE_URL + "/9")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound()); // Assert HTTP status code
    }

    @Test
    @Description("Student NOT found Exception")
    public void getStudentById_ResourceNotFoundExceptionTest() throws Exception {

        
        // Mock the service to throw ResourceNotFoundException when getStudentById is called
        when(studentService.getStudentById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc = MockMvcBuilders.standaloneSetup(studentController)
                .setControllerAdvice(new GlobalExceptionHandler()) // add global exception handler
                .build();
        
        // Perform GET request and assert the response status
        mockMvc.perform(MockMvcRequestBuilders
                .get(BASE_URL + "/9")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound()); // Assert HTTP status code
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
