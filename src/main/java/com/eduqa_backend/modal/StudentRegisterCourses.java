package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.eduqa_backend.enums.StudentCourseStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class StudentRegisterCourses {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, targetEntity = Registration.class)
    private Registration registration;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, targetEntity = LectureCourse.class)
    private LectureCourse lectureCourse;
    private LocalDateTime timeStamp;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudentCourseStatus status;
    public StudentRegisterCourses(StudentCourseStatus status, Registration registration, LectureCourse lectureCourse) {
        this.registration = registration;
        this.lectureCourse = lectureCourse;
        this.timeStamp = LocalDateTime.now();
        this.status = status;
    }
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "studentCourse",targetEntity = Attendance.class)
    private List<AttendanceRecord> attendanceList ;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "studentCourse",targetEntity = Deliberation.class)
    private List<Deliberation> deliberations ;
}
