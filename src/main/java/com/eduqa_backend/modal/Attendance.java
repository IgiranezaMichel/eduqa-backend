package com.eduqa_backend.modal;

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
@ManyToOne(cascade = CascadeType.ALL,targetEntity = StudentRegisterCourses.class)
private StudentRegisterCourses studentCourse;
private boolean isPresent;
public Attendance(AttendanceInput attendanceInput,StudentRegisterCourses studentCourse) {
    if(!attendanceInput.getId().equals(""))this.id=UUID.fromString(attendanceInput.getId());
    this.studentCourse = studentCourse;
    this.isPresent = attendanceInput.isPresent();
}
}
