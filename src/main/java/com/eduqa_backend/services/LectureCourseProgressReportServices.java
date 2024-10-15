package com.eduqa_backend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.modal.LectureCourseContent;
import com.eduqa_backend.modal.LectureCourseProgressReport;
import com.eduqa_backend.repository.LectureCourseContentRepository;
import com.eduqa_backend.repository.LectureCourseProgressReportRepository;

@Service
public class LectureCourseProgressReportServices {
@Autowired private LectureCourseProgressReportRepository lectureCourseProgressReportRepository;
@Autowired private LectureCourseContentRepository lectureCourseContentRepository;

public ResponseEntity<String> createCourseProgressReport(int currentChapter, String lectureCourseContent) {
try {
    LectureCourseContent lcc=lectureCourseContentRepository.findById(UUID.fromString(lectureCourseContent)).orElseThrow(()-> new RuntimeException("Lecture Course Content not found"));
    lectureCourseProgressReportRepository.save(new LectureCourseProgressReport(currentChapter, lcc));
     return ResponseEntity.ok("Course progress report created successfully");
} catch (Exception e) {
return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating course progress report");
}
}
public int getCurrentChapter(){
    return lectureCourseProgressReportRepository.findFirstByOrderByTimeStampDesc().get().getCurrentChapter();
}
}
