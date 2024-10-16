package com.eduqa_backend.input;

import com.eduqa_backend.enums.ContentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureCourseProgressReportInput {
  private String id;
    private double currentChapter;
    private String lectureCourseContentId;
    private String title;
    private ContentType type;
}
