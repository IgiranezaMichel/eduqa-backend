package com.eduqa_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewLectureDTO {
    private UserDTO user;
    private Object marks;

    public ReviewLectureDTO(ReviewLecture rl) {
        this.user = new UserDTO(rl.getLecture());
        if (rl.getAverageReview() != null)
            this.marks = rl.getAverageReview();
    }
}
