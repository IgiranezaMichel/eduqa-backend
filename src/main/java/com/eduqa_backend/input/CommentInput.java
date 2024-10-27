package com.eduqa_backend.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInput {
private String comment;
@JsonProperty("lCourseProgressReportId")
private String lCourseProgressReportId;
}
