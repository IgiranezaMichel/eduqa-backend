package com.eduqa_backend.dto;

import com.eduqa_backend.modal.Course;
import com.eduqa_backend.modal.Semester;

import lombok.Getter;
import lombok.Setter;

public class SemesterCourseDTO extends CourseDTO{
    @Getter @Setter
private SemesterDTO semester;

public SemesterCourseDTO(Course course, Semester semester) {
    super(course);
    this.semester = new SemesterDTO(semester);
}

}
