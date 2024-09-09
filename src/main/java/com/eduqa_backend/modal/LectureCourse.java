package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.UUID;

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
public class LectureCourse {
@Id
private UUID id;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = User.class)
private User user;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = Course.class)
private Course course;
private LocalDateTime timeStamp=LocalDateTime.now();

public LectureCourse(UUID id, User user, Course course) {
    this.id = id;
    this.user = user;
    this.course = course;
    this.timeStamp = LocalDateTime.now();
}

public LectureCourse(User user, Course course) {
    this.user = user;
    this.course = course;
    this.timeStamp = LocalDateTime.now();
}

}
