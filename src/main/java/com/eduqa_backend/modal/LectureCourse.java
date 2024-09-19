package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LectureCourse {
@Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = User.class)
private User user;

@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = Course.class)
private Course course;
@Column(name="lecture_group")
private String group;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = Semester.class)
private Semester semester;

private LocalDateTime timeStamp=LocalDateTime.now();
public LectureCourse(UUID id, User user, Course course,Semester semester) {
    this.id = id;
    this.user = user;
    this.course = course;
    this.timeStamp = LocalDateTime.now();
    this.semester=semester;
}
public LectureCourse(User user, Course course,Semester semester) {
    this.user = user;
    this.course = course;
    this.timeStamp = LocalDateTime.now();
    this.semester=semester;
}

}
