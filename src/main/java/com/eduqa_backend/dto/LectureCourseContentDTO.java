package com.eduqa_backend.dto;

import com.eduqa_backend.modal.LectureCourseContent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class LectureCourseContentDTO extends UserDTO {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private int totalChapter;

    public LectureCourseContentDTO(LectureCourseContent lc) {
        super(lc.getLectureCourse().getUser());
        this.id = lc.getId().toString();
        this.description = lc.getDescription();
        this.totalChapter = lc.getTotalChapter();
    }
}
