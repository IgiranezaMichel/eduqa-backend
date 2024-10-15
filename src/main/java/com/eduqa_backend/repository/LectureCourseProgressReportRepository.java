package com.eduqa_backend.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eduqa_backend.modal.LectureCourseProgressReport;
public interface LectureCourseProgressReportRepository extends JpaRepository<LectureCourseProgressReport,UUID> {
Optional<LectureCourseProgressReport> findFirstByOrderByTimeStampDesc();
}
