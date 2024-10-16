package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.eduqa_backend.enums.ContentType;
import com.eduqa_backend.input.LectureCourseProgressReportInput;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureCourseProgressReport {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    private double currentChapter;
    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = LectureCourseContent.class, optional = false, fetch = FetchType.LAZY)
    private LectureCourseContent lectureCourseContent;
    private String title;
    @Enumerated(EnumType.STRING)
    private ContentType type;
    private LocalDateTime timeStamp;

    public LectureCourseProgressReport(LectureCourseProgressReportInput in, LectureCourseContent lectureCourseContent) {
        if (in.getId() != null)
            this.id = UUID.fromString(in.getId());
        this.currentChapter = in.getCurrentChapter();
        this.lectureCourseContent = lectureCourseContent;
        this.title = in.getTitle();
        this.type = in.getType();
        this.timeStamp = LocalDateTime.now();
    }
}
