package com.harsh.learningnavigator.student.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.harsh.learningnavigator.exam.entity.Exam;
import com.harsh.learningnavigator.subject.entity.Subject;

import jakarta.persistence.Entity;
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

/**
 * Entity class representing a student.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    /** Unique registration ID for the student. */
    Long registrationId;

    /** The name of the student. */
    String name;
    
    /* 
    Each student can be enrolled in multiple subjects, 
    and each subject can have multiple students enrolled in it.
    therefore many to many relation 
    */
    /** 
     * Set of subjects in which the student is enrolled.
     * Many-to-many relationship with Subject entities.
     */
    @ManyToMany
    Set<Subject> enrolledSubjects = new HashSet<>();

    /*
    Each student can be registered for multiple exams, 
    and each exam can have multiple students registered for it.
    therefore many to many relation
    */
    /** 
     * Set of exams for which the student is registered.
     * Many-to-many relationship with Exam entities.
     */
    @ManyToMany
    @JoinTable(name = "Student_Exam_Mapping", joinColumns = @JoinColumn(name = "student_id"), 
        	inverseJoinColumns = @JoinColumn(name = "exam_id"))
    Set<Exam> registeredExams = new HashSet<>();

    /**
     * Adds a subject to the list of enrolled subjects for the student.
     * 
     * @param subject The subject to add.
     * @return true if the subject was added successfully, false otherwise.
     */
    public boolean addSubject(Subject subject) {
        return enrolledSubjects.add(subject);
    }

    /**
     * Enrolls the student to an exam.
     * 
     * @param exam The exam to enroll in.
     * @return true if the student was enrolled successfully, false otherwise.
     */
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
