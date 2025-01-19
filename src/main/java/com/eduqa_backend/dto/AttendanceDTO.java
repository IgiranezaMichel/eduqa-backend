package com.eduqa_backend.dto;

import java.time.LocalDateTime;
import com.eduqa_backend.modal.Attendance;
import com.eduqa_backend.util.DateConverter;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {
private String id;
private String lectureCourseId;
private CourseDTO course;
private String date;
private String timeStamp;
public AttendanceDTO(Attendance     attendance) {
    if(attendance.getId()!=null) 
        this.id = attendance.getId().toString();
    this.lectureCourseId = attendance.getLectureCourse().getId().toString();
    this.course = new CourseDTO(attendance.getLectureCourse().getCourse());
    this.date =DateConverter.LocalDateTimeConverter(attendance.getDate(), "MMM dd, yyyy hh:mm a") ;;
    this.timeStamp =DateConverter.LocalDateTimeConverter(attendance.getTimeStamp(), "MMM dd, yyyy hh:mm a") ;
}
}
