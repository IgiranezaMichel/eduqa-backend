package com.eduqa_backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.eduqa_backend.dto.ReviewLecture;
import com.eduqa_backend.dto.StudentReviewDTO;
import com.eduqa_backend.modal.CourseReview;

public interface CourseReviewRepository extends JpaRepository<CourseReview,UUID>{
@Query("""
        SELECT new com.eduqa_backend.dto.StudentReviewDTO(cr.id,lc.course.name,lc.course.code,lc.user.picture,lc.user.name,lc.user.email,COUNT(cr.marks)) FROM  CourseReview cr LEFT JOIN LectureCourse lc ON cr.lectureCourse.id=lc.id
        WHERE cr.user.email=:email GROUP BY cr.id,lc.course.name,lc.course.code,lc.user.picture,lc.user.name,lc.user.email
        """)
    List<StudentReviewDTO> findAllStudentCourseReviews(String email);
@Query("""
SELECT new com.eduqa_backend.dto.ReviewLecture(
    cr.lectureCourse.user,
     AVG(cr.marks))
FROM CourseReview cr
 RIGHT JOIN cr.lectureCourse.user u  WHERE cr.lectureCourse.user.role=com.eduqa_backend.enums.Role.ROLE_INSTRACTOR group by cr.lectureCourse.user.id
        """)
List<ReviewLecture>listOfPrincipleRectureReview();
Optional<CourseReview> findByUserEmailAndLectureCourseId(String name, UUID fromString);
}
