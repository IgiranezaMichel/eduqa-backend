package com.eduqa_backend.modal;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
    private int currentChapter;
    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = LectureCourseContent.class, optional = false, fetch = FetchType.LAZY)
    private LectureCourseContent lectureCourseContent;
}
