package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import com.eduqa_backend.input.LectureCourseProgressReportInput;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

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
    private LocalDateTime timeStamp;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "lectureCourseProgressReport")
    private List<com.eduqa_backend.modal.LectureCourseProgressComment> LectureCourseProgressComment;

    public LectureCourseProgressReport(LectureCourseProgressReportInput in, LectureCourseContent lectureCourseContent) {
        if (!in.getId().equals(""))
            this.id = UUID.fromString(in.getId());
        this.currentChapter = in.getCurrentChapter();
        this.lectureCourseContent = lectureCourseContent;
        this.title = in.getTitle();
        this.timeStamp = LocalDateTime.now();
    }
}
