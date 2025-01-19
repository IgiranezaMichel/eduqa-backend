package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import com.eduqa_backend.input.AttendanceRecordInput;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceRecord {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = StudentRegisterCourses.class)
    private StudentRegisterCourses studentCourse;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Attendance.class)
    private Attendance attendance;
    private boolean isPresent;
    private LocalDateTime timeStamp;

    public AttendanceRecord(AttendanceRecordInput aInput, StudentRegisterCourses studentCourse,Attendance attendance) {
        if (aInput.getId()!=null)
            this.id = UUID.fromString(aInput.getId());
        this.studentCourse = studentCourse;
        this.attendance=attendance;
        this.isPresent = aInput.isPresent();
        this.timeStamp = LocalDateTime.now();
    }

}
