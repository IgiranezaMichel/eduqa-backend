package com.eduqa_backend.input;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceRecordInput {
private String id;
private String studentCourseId;
private boolean isPresent;
}
