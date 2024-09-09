package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduqa_backend.repository.LectureCourseRepository;

@Service
public class LectureCourseServices {
@Autowired private LectureCourseRepository lectureCourseRepository;
}
