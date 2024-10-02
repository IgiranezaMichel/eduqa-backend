package com.eduqa_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureCourseOverviewDTO {
 private Object lectureCourseId;
 private Object lectureCourseCode;
 private Object lectureCourseName;
 private Object lectureCourseGroup;
 private Object lectureCourseCredit;
 private Object lectureCourseDuration;
 private Object currentChapter;
 private Object totalStudent;
 private Object totalChapter;
}
