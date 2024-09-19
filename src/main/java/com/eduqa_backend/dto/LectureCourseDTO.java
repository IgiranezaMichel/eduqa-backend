package com.eduqa_backend.dto;

import com.eduqa_backend.modal.Course;
import com.eduqa_backend.modal.Semester;
import com.eduqa_backend.modal.User;
import com.eduqa_backend.util.DateConverter;

import lombok.Getter;
import lombok.Setter;

public class LectureCourseDTO extends UserDTO{
@Getter @Setter
private CourseDTO course;
@Getter @Setter
private String semesterName;
@Getter @Setter
private String semesterStartingDate;
@Getter @Setter
private String semesterEndingDate;
public LectureCourseDTO(User user, Course course,Semester semester) {
    super(user);
    this.course = new CourseDTO(course);
    this.semesterName = semester.getName();
    this.semesterEndingDate =DateConverter.LocalDateConverter(semester.getEndDate(), "yyy,MMM,dd");
    this.semesterStartingDate =DateConverter.LocalDateConverter(semester.getStartingDate(), "yyy,MMM,dd");
}

}
