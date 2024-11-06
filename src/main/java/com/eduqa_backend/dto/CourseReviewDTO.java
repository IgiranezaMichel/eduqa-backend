package com.eduqa_backend.dto;

import com.eduqa_backend.modal.CourseReview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseReviewDTO {
private String id;
private UserDTO user;
private UserDTO lecture;
private double marks;
public CourseReviewDTO(CourseReview cv){
    this.id=cv.getId().toString();
    this.user=new UserDTO(cv.getUser());
    this.lecture=new UserDTO(cv.getLectureCourse().getUser());
    this.marks=cv.getMarks();
}
}
