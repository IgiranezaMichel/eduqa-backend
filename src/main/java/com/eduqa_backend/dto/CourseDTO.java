package com.eduqa_backend.dto;
import com.eduqa_backend.modal.Course;
import com.eduqa_backend.util.DateConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
private String id;
private String code;
private String name;
private int credit;
private String timeStamp;
private int duration;
public CourseDTO(Course course) {
    this.id = course.getId().toString();
    this.code = course.getCode();
    this.name = course.getName();
    this.credit = course.getCredit();
    this.duration = course.getDuration();
    this.timeStamp = DateConverter.LocalDateTimeConverter(course.getTimeStamp(), "dd, MMMM, yyyy MM:ss a");
}


}
