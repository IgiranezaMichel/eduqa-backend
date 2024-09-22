package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.eduqa_backend.input.LectureCourseInput;
import java.util.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public LectureCourse(LectureCourseInput input,User user, Course course,Semester semester) {
   if(input.getId()!=null&&!input.getId().isEmpty())
    this.id = UUID.fromString(input.getId());
    this.user = user;
    this.course = course;
    this.timeStamp = LocalDateTime.now();
    this.semester=semester;
    this.group=input.getGroup();
}
@OneToMany(mappedBy = "lectureCourse",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = StudentRegisterCourses.class)
public List<StudentRegisterCourses> studentRegistrationCourses;
}
