package com.harsh.learningnavigator.services.students;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;

import com.harsh.learningnavigator.dto.StudentDto;
import com.harsh.learningnavigator.dto.StudentReqeustDto;
import com.harsh.learningnavigator.entity.Student;
import com.harsh.learningnavigator.exception.ResourceNotFoundException;
import com.harsh.learningnavigator.repository.StudentRepository;
import com.harsh.learningnavigator.repository.SubjectRepository;
import com.harsh.learningnavigator.services.student.implementations.StudentServiceImplementation;

@SpringBootTest
public class StudentServiceTest {
    
    @InjectMocks
    private StudentServiceImplementation studentService;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private SubjectRepository subjectRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    public void setUp() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImplementation(studentRepository, subjectRepository, modelMapper);
    }

    @Test
    public void addStudentTest() {
        Student student = new Student();
        student.setName("Harsh");
        student.setRegistrationId(1L);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDto studentDto = studentService.addStudent(new StudentReqeustDto("Harsh"));

        assertEquals(student.getName(), studentDto.getName());
    }


    @Test
    @Description("IllegalArgumentException Check when id is null")
    public void getStudentByIdIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> studentService.getStudentById(null));
    }

    @Test
    @Description("ResourceNotFoundException Check when student is not found")
    public void getStudentByIdResourceNotFoundExceptionTest() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentById(10L));
    }

    @Test
    @Description("IllegalArgumentException Check when student id is null")
    public void updateStudentIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> studentService.updateStudent(null, new StudentDto()));
    }

    @Test
    @Description("IllegalArgumentException Check when StudentDto is null")
    public void updateStudentResourceNotFoundExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> studentService.updateStudent(1L, null));
    }

    @Test
    @Description("IllegalArgumentException Check when student id is null")
    public void deleteIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> studentService.delete(null));
    }

    @Test
    @Description("ResourceNotFoundException Check when student is not found")
    public void deleteStudentTest() {
        Student student = new Student();
        student.setName("Harsh");
        student.setRegistrationId(1L);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.delete(1L));

        
    }

}
