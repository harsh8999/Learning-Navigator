package com.harsh.learningnavigator.subject.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Data Transfer Object (DTO) representing a subject.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectDto {

    /** Unique identifier for the subject DTO. */
    Long id;

    /** The name of the subject DTO. */
    String subjectName;
    
}
