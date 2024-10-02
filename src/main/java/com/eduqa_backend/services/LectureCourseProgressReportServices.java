package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduqa_backend.repository.LectureCourseProgressReportRepository;

@Service
public class LectureCourseProgressReportServices {
@Autowired private LectureCourseProgressReportRepository lectureCourseProgressReportRepository;
}
