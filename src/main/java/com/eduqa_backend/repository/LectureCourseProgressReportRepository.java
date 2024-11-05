package com.eduqa_backend.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eduqa_backend.modal.LectureCourseContent;
import com.eduqa_backend.modal.LectureCourseProgressReport;

public interface LectureCourseProgressReportRepository extends JpaRepository<LectureCourseProgressReport, UUID> {
    Optional<LectureCourseProgressReport> findFirstByLectureCourseContentLectureCourseIdOrderByTimeStampDesc(UUID id);

    List<LectureCourseProgressReport> findAllByLectureCourseContentLectureCourseId(UUID fromString);

    Optional<LectureCourseProgressReport> findByLectureCourseContent(LectureCourseContent lcc);

    LectureCourseProgressReport findFirstByLectureCourseContentOrderByTimeStampDesc(LectureCourseContent lcc);
    @Query("""
        SELECT lcpr 
        FROM LectureCourseProgressReport lcpr
        WHERE lcpr.timeStamp = (
            SELECT MAX(inner1.timeStamp)
            FROM LectureCourseProgressReport inner1
            WHERE inner1.lectureCourseContent.lectureCourse = lcpr.lectureCourseContent.lectureCourse
            AND inner1.lectureCourseContent.lectureCourse.semester.id = :semesterId
        )
        ORDER BY lcpr.currentChapter DESC
    """)
    List<LectureCourseProgressReport> findAllLatestLectureProgressReport(UUID semesterId);
    

}
