package com.harsh.learningnavigator.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long registrationId;

    String name;
    
    /* 
    Each student can be enrolled in multiple subjects, 
    and each subject can have multiple students enrolled in it.
    therefore many to many relation 
    */
    @ManyToMany
    Set<Subject> enrolledSubjects = new HashSet<>();

    /*
    Each student can be registered for multiple exams, 
    and each exam can have multiple students registered for it.
    therefore many to many relation
    */
    @ManyToMany
    @JoinTable(name = "Student_Exam_Mapping", joinColumns = @JoinColumn(name = "student_id"), 
        	inverseJoinColumns = @JoinColumn(name = "exam_id"))
    Set<Exam> registeredExams = new HashSet<>();

    public boolean addSubject(Subject subject) {
        return enrolledSubjects.add(subject);
    }

    public boolean enrollStudentToExam(Exam exam) {
        return registeredExams.add(exam);
    }
    

    @Override
    public int hashCode() {
        return Objects.hash(registrationId, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return Objects.equals(registrationId, other.registrationId) && Objects.equals(name, other.name);
    }

}
