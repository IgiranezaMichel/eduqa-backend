package com.eduqa_backend.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.dto.LectureCourseProgressReportDTO;
import com.eduqa_backend.input.LectureCourseProgressReportInput;
import com.eduqa_backend.modal.LectureCourseContent;
import com.eduqa_backend.modal.LectureCourseProgressReport;
import com.eduqa_backend.repository.LectureCourseContentRepository;
import com.eduqa_backend.repository.LectureCourseProgressReportRepository;

@Service
public class LectureCourseProgressReportServices {
    @Autowired
    private LectureCourseProgressReportRepository lectureCourseProgressReportRepository;
    @Autowired
    private LectureCourseContentRepository lectureCourseContentRepository;

    public ResponseEntity<String> createCourseProgressReport(LectureCourseProgressReportInput lcci) {
        try {
            LectureCourseContent lcc = lectureCourseContentRepository
                    .findById(UUID.fromString(lcci.getLectureCourseContentId()))
                    .orElseThrow(() -> new RuntimeException("Lecture Course Content not found"));
            LectureCourseProgressReport lcpr = lectureCourseProgressReportRepository
                    .findFirstByLectureCourseContentOrderByTimeStampDesc(lcc);
            if (lcpr != null) {
                if ((int) lcpr.getCurrentChapter() < lcpr.getLectureCourseContent().getTotalChapter()) {
                    lcci.setCurrentChapter(lcpr.getCurrentChapter() + 1);
                } else {
                    return new ResponseEntity<>("Course content has exceed it number", HttpStatus.BAD_REQUEST);
                }
            }
            lectureCourseProgressReportRepository.save(new LectureCourseProgressReport(lcci, lcc));
            return ResponseEntity.ok("Course progress report created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public double getCurrentChapter(String lectureCourseId) {
        return lectureCourseProgressReportRepository
                .findFirstByLectureCourseContentLectureCourseIdOrderByTimeStampDesc(UUID.fromString(lectureCourseId))
                .orElse(null).getCurrentChapter();
    }

    public List<LectureCourseProgressReportDTO> getAllCourseContent(String lectureCourseId) {
        return lectureCourseProgressReportRepository
                .findAllByLectureCourseContentLectureCourseId(UUID.fromString(lectureCourseId)).stream()
                .map(LectureCourseProgressReportDTO::new)
                .toList();
    }

    public List<LectureCourseProgressReportDTO> findAllLatestLectureProgressReport(String semesterId) {
        return lectureCourseProgressReportRepository
                .findAllLatestLectureProgressReport(UUID.fromString(semesterId)).stream()
                .map(LectureCourseProgressReportDTO::new)
                .toList();
    }
}
