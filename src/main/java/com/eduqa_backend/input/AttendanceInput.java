package com.eduqa_backend.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceInput {
private String id;
private String studentRegisteredId;
private boolean isPresent;
}
