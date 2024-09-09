package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduqa_backend.repository.CourseRepository;

@Service
public class CourseServices {
@Autowired private CourseRepository courseRepository;
}
