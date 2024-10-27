package com.eduqa_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.eduqa_backend.modal.LectureCourseProgressComment;
@Repository
public interface LectureCourseProgressCommentRepository extends JpaRepository<LectureCourseProgressComment,UUID> {
    List<LectureCourseProgressComment> findAllById(UUID fromString);
    List<LectureCourseProgressComment> findAllByLectureCourseProgressReportId(UUID lectureCourseProgressReportId);

}
