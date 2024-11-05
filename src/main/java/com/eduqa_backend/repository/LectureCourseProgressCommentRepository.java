package com.eduqa_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.eduqa_backend.modal.LectureCourseProgressComment;
@Repository
public interface LectureCourseProgressCommentRepository extends JpaRepository<LectureCourseProgressComment,UUID> {
    List<LectureCourseProgressComment> findAllById(UUID fromString);
    List<LectureCourseProgressComment> findAllByLectureCourseProgressReportId(UUID lectureCourseProgressReportId);
@Query("""
    SELECT lcpc FROM LectureCourseProgressComment lcpc
    JOIN lcpc.user u
    WHERE lcpc.timeStamp = (
        SELECT MAX(innerLcpc.timeStamp)
        FROM LectureCourseProgressComment innerLcpc
        WHERE innerLcpc.lectureCourseProgressReport.lectureCourseContent = lcpc.lectureCourseProgressReport.lectureCourseContent and 
        lcpc.lectureCourseProgressReport.lectureCourseContent.lectureCourse.semester.id=:semesterId
    )
""")
List<LectureCourseProgressComment> findLatestMessageForEachCourseContentReport(UUID semesterId);

}
