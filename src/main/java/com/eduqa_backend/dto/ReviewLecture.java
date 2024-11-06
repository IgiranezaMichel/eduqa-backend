package com.eduqa_backend.dto;

import com.eduqa_backend.modal.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewLecture{
    private User lecture;
    private Object averageReview;
}
