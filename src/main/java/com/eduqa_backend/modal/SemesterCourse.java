package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SemesterCourse {
 @Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = Semester.class)
private Semester semester;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = Course.class)
private Course course;
private LocalDateTime timeStamp=LocalDateTime.now();
public SemesterCourse(String id, Semester semester, Course course) {
    this.id = UUID.fromString(id);
    this.semester = semester;
    this.course = course;
    this.timeStamp = LocalDateTime.now();
}
public SemesterCourse(Semester semester, Course course) {
    this.semester = semester;
    this.course = course;
    this.timeStamp = LocalDateTime.now();
}
}
