package com.eduqa_backend.dto;

import java.time.LocalDateTime;
import com.eduqa_backend.modal.Attendance;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {
private String id;
private String lectureCourseId;
private CourseDTO course;
private LocalDateTime date;
private LocalDateTime timeStamp;
public AttendanceDTO(Attendance     attendance) {
    if(attendance.getId()!=null) 
        this.id = attendance.getId().toString();
    this.lectureCourseId = attendance.getLectureCourse().getId().toString();
    this.course = new CourseDTO(attendance.getLectureCourse().getCourse());
    this.date = attendance.getDate();
    this.timeStamp = attendance.getTimeStamp();
}
}
