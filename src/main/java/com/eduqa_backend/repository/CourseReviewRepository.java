package com.eduqa_backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eduqa_backend.dto.StudentReviewDTO;
import com.eduqa_backend.modal.CourseReview;

public interface CourseReviewRepository extends JpaRepository<CourseReview,UUID>{
@Query("""
        SELECT new com.eduqa_backend.dto.StudentReviewDTO(cr.id,lc.course.name,lc.course.code,lc.user.picture,lc.user.name,lc.user.email,COUNT(cr.marks)) FROM  CourseReview cr LEFT JOIN LectureCourse lc ON cr.lectureCourse.id=lc.id
        WHERE cr.user.email=:email GROUP BY cr.id,lc.course.name,lc.course.code,lc.user.picture,lc.user.name,lc.user.email
        """)
    List<StudentReviewDTO> findAllStudentCourseReviews(String email);
}
