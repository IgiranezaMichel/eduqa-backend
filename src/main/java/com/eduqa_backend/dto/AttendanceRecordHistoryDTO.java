package com.eduqa_backend.dto;

import com.eduqa_backend.modal.User;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class AttendanceRecordHistoryDTO {
private Object id;
private Object isPresent;
private Object timeStamp;
private Object attendanceDate;
private Object studentCourseId;
private UserDTO student;
public AttendanceRecordHistoryDTO(Object id, Object isPresent, Object timeStamp,Object studentCourseId, User student,Object attendanceDate) {
    this.id = id;
    this.isPresent = isPresent;
    this.attendanceDate=attendanceDate;
    this.timeStamp = timeStamp;
    this.studentCourseId = studentCourseId;
    this.student = new UserDTO(student);
}
}
