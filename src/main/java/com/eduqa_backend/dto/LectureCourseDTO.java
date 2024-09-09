package com.eduqa_backend.dto;

import com.eduqa_backend.modal.Course;
import com.eduqa_backend.modal.User;

import lombok.Getter;
import lombok.Setter;

public class LectureCourseDTO extends UserDTO{
@Getter @Setter
private CourseDTO course;
public LectureCourseDTO(User user, Course course) {
    super(user);
    this.course = new CourseDTO(course);
}

}
