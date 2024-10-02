package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduqa_backend.repository.LectureCourseSuggestionRepository;

@Service
public class LectureCourseSuggestionServices {
@Autowired private LectureCourseSuggestionRepository lectureCourseSuggestionRepository;
}
