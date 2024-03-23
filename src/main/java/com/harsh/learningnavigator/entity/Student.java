package com.harsh.learningnavigator.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    List<Subject> enrolledSubjects;

    /*
    Each student can be registered for multiple exams, 
    and each exam can have multiple students registered for it.
    therefore many to many relation
    */
    @ManyToMany
    List<Exam> registeredExams;

    public Student(Long registrationId, String name) {
        this.registrationId = registrationId;
        this.name = name;
        this.enrolledSubjects = new ArrayList<>();
        this.registeredExams = new ArrayList<>();
    }

    

}
