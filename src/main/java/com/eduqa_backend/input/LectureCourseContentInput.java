package com.eduqa_backend.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureCourseContentInput {
private String id;
private String description;
private int totalChapter;
private String lectureCourseId;
}
