package com.eduqa_backend.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterCourseInput {
private String id;
private String semesterId;
private String courseId;
}
