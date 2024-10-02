package com.eduqa_backend.modal;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.eduqa_backend.input.LectureCourseContentInput;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class LectureCourseContent {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(columnDefinition = "text")
    private String description;
    private int totalChapter;
    @OneToOne(cascade = CascadeType.ALL, targetEntity = LectureCourse.class, optional = false)
    private LectureCourse lectureCourse;

    @OneToMany(mappedBy = "lectureCourseContent", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, targetEntity = LectureCourseProgressReport.class)
    private List<LectureCourseProgressReport> lectureCourseProgressReports;

    public LectureCourseContent(LectureCourseContentInput input, LectureCourse lc) {
        if (input.getId()!=null)
            this.id = UUID.fromString(input.getId());
        this.description = input.getDescription();
        this.totalChapter = input.getTotalChapter();
        this.lectureCourse = lc;
    }
}
