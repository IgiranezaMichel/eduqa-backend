package com.eduqa_backend.input;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceInput {
private String id;
private String lectureCourseId;
private LocalDateTime date;
private LocalDateTime timeStamp;

}
