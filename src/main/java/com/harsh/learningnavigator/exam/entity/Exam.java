package com.harsh.learningnavigator.exam.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harsh.learningnavigator.student.entity.Student;
import com.harsh.learningnavigator.subject.entity.Subject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Entity class representing an exam.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Exam {
    
    /** Unique identifier for the exam. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    /*
    A subject can have multiple exam but exam has only one subject
    */
    /** The subject of the exam. */
    @ManyToOne
    Subject subject;


    /** 
     * Set of students registered for the exam.
     * Many-to-many relationship with Student entities.
     */
    @JsonIgnore // using this we can prevent circular reference problem
    @ManyToMany
    @JoinTable(name = "Student_Exam_Mapping", joinColumns = @JoinColumn(name = "exam_id"), 
        	inverseJoinColumns = @JoinColumn(name = "student_id"))
    Set<Student> registeredStudents = new HashSet<>();


    /**
     * Registers a student for the exam.
     * 
     * @param student The student to register.
     * @return true if the student was successfully registered, false otherwise.
     */
    public boolean registerStudent(Student student) {
        return this.registeredStudents.add(student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Exam)) return false;
        Exam other = (Exam) obj;
        return Objects.equals(id, other.id) && Objects.equals(subject, other.subject);
    }
    
}
