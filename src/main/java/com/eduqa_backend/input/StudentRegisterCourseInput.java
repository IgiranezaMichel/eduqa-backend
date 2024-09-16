package com.eduqa_backend.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRegisterCourseInput {
    private String id;
    private String courseId;
    private String registrationId;
}
