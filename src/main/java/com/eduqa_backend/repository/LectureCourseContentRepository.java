package com.eduqa_backend.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eduqa_backend.modal.LectureCourse;
import com.eduqa_backend.modal.LectureCourseContent;

public interface LectureCourseContentRepository extends JpaRepository<LectureCourseContent,UUID>{
    Optional<LectureCourseContent> findByLectureCourse(LectureCourse lc);
}
