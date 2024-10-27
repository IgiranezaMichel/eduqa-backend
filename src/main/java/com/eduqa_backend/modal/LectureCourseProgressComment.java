package com.eduqa_backend.modal;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import java.time.*;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class LectureCourseProgressComment {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    @Column(columnDefinition = "text")
    private String message;
    private LocalDateTime timeStamp;
    @ManyToOne(cascade = CascadeType.REMOVE, optional = false, targetEntity = User.class)
    private User user;
    @ManyToOne(cascade = CascadeType.REMOVE, optional = false, targetEntity = LectureCourseProgressReport.class)
    private LectureCourseProgressReport lectureCourseProgressReport;
    public LectureCourseProgressComment(String message, User user,
            LectureCourseProgressReport lectureCourseProgressReport) {
        this.message = message;
        this.user = user;
        this.timeStamp=LocalDateTime.now();
        this.lectureCourseProgressReport = lectureCourseProgressReport;
    }
}
