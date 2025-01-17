package com.eduqa_backend.dto;

import com.eduqa_backend.modal.Attendance;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {
private String id;
private UserDTO student;
private boolean isPresent;
public AttendanceDTO(Attendance     attendance) {
    if(attendance.getId()!=null) 
        this.id = attendance.getId().toString();
        this.student = new UserDTO(attendance.getStudentCourse().getRegistration().getUser());
        this.isPresent = attendance.isPresent();
}
}
