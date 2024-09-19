package com.eduqa_backend.input;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureCourseInput {
private String id;
private String userId;
private String courseId;
private String group;
private String semesterId;
}
