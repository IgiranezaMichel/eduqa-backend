package com.eduqa_backend.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliberationInput {
    private String id;
    private String studentRegisteredId;
    private boolean hasPassed;
}
