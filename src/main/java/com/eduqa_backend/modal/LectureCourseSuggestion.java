package com.eduqa_backend.modal;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LectureCourseSuggestion {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    private int chapter;
    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = LectureCourse.class, optional = false, fetch = FetchType.LAZY)
    private LectureCourse lectureCourse;
    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = User.class, optional = false, fetch = FetchType.LAZY)
    private User user;

}
