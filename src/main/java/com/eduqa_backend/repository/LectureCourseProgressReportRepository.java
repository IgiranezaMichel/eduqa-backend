package com.eduqa_backend.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eduqa_backend.modal.LectureCourseContent;
import com.eduqa_backend.modal.LectureCourseProgressReport;

public interface LectureCourseProgressReportRepository extends JpaRepository<LectureCourseProgressReport, UUID> {
    Optional<LectureCourseProgressReport> findFirstByLectureCourseContentLectureCourseIdOrderByTimeStampDesc(UUID id);
    List<LectureCourseProgressReport> findAllByLectureCourseContentLectureCourseId(UUID fromString);
    Optional<LectureCourseProgressReport> findByLectureCourseContent(LectureCourseContent lcc);
    LectureCourseProgressReport findFirstByLectureCourseContentOrderByTimeStampDesc(LectureCourseContent lcc);
}
