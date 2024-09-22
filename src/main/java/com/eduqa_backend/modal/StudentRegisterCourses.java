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
@Data
@NoArgsConstructor
@Entity
public class StudentRegisterCourses {
@Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = Registration.class)
private Registration registration;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = LectureCourse.class)
private LectureCourse lectureCourse;
private LocalDateTime timeStamp;

public StudentRegisterCourses(String id,Registration registration, LectureCourse lectureCourse) {
    if(!id.isEmpty()){
        this.id=UUID.fromString(id);
    }
    this.registration = registration;
    this.lectureCourse = lectureCourse;
    this.timeStamp=LocalDateTime.now();
}
}
