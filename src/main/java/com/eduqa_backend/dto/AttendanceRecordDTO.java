package com.eduqa_backend.dto;

import com.eduqa_backend.modal.User;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class AttendanceRecordDTO {
private Object id;
private Object isPresent;
private Object timeStamp;
private Object studentCourseId;
private UserDTO student;
AttendanceRecordDTO(Object id, Object isPresent, Object timeStamp,Object studentCourseId, User student) {
    this.id = id;
    this.isPresent = isPresent;
    this.timeStamp = timeStamp;
    this.studentCourseId = studentCourseId;
    this.student = new UserDTO(student);
}
}
