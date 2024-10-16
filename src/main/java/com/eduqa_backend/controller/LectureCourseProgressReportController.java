package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.input.LectureCourseProgressReportInput;
import com.eduqa_backend.services.LectureCourseProgressReportServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/lecture-course-progress-report")
public class LectureCourseProgressReportController {
@Autowired private LectureCourseProgressReportServices lectureCourseProgressReportServices;
@GetMapping("register/{lectureCourseContent}")
public ResponseEntity<String> getMethodName(@RequestBody LectureCourseProgressReportInput input) {
    return lectureCourseProgressReportServices.createCourseProgressReport(input);
}
@GetMapping("current-chapter")
public double getCurrentChapter(@RequestParam String param) {
    return lectureCourseProgressReportServices.getCurrentChapter();
}

}
