package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    @Column(name = "code", unique = true)
    private String code;
    private String name;
    @Column(name = "courseDuration")
    private int duration;
    private int credit;
    private LocalDateTime timeStamp = LocalDateTime.now();

    public Course(String code, String name, byte credit, LocalDateTime timeStamp) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.timeStamp = timeStamp;
        this.duration = credit * 1;
    }

    public Course(String id, String code, String name, byte credit, LocalDateTime timeStamp) {
        this.id = UUID.fromString(id);
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.timeStamp = timeStamp;
        this.duration = credit * 1;

    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "course", targetEntity = LectureCourse.class)
    public List<LectureCourse> lectureCourses;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "course", targetEntity = SemesterCourse.class)
    public List<SemesterCourse> semesterCourses;
}
