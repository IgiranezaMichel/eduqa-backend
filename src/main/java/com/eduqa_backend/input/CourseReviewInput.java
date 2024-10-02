package com.eduqa_backend.input;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseReviewInput {
   private String id;
    private String lectureCourseId;
    private String userId;
    private int marks;
}

