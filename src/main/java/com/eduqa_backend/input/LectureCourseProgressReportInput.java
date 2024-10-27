package com.eduqa_backend.input;

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
}
