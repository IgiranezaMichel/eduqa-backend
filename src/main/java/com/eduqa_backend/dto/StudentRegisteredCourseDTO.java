package com.eduqa_backend.dto;

import com.eduqa_backend.modal.StudentRegisterCourses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRegisteredCourseDTO {
private String id;
private String lectureCourseId;
private UserDTO lecture;
private UserDTO student;    
private CourseDTO course;
private SemesterDTO semester;
public StudentRegisteredCourseDTO(StudentRegisterCourses data){
    this.id=data.getId().toString();
    this.lectureCourseId=data.getLectureCourse().getId().toString();
    this.student=new UserDTO(data.getRegistration().getUser());
    this.lecture=new UserDTO(data.getLectureCourse().getUser());
    this.course=new CourseDTO(data.getLectureCourse().getCourse());
    this.semester=new SemesterDTO(data.getRegistration().getSemester());
}
}
