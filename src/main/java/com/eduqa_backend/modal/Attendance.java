package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.eduqa_backend.input.AttendanceInput;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attendance {
@Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
@ManyToOne(cascade = CascadeType.ALL)
private LectureCourse lectureCourse;
private LocalDateTime date;
private LocalDateTime timeStamp;
public Attendance(AttendanceInput attendanceInput,LectureCourse lectureCourse) {
    if(!attendanceInput.getId().equals(""))this.id=UUID.fromString(attendanceInput.getId());
    this.lectureCourse = lectureCourse;
    this.date=attendanceInput.getDate();
    this.timeStamp = LocalDateTime.now();
}
}
