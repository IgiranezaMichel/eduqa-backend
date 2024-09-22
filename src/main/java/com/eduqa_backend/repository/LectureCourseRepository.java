package com.eduqa_backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.eduqa_backend.modal.Course;
import com.eduqa_backend.modal.LectureCourse;
import com.eduqa_backend.modal.User;
@Repository
public interface LectureCourseRepository extends JpaRepository<LectureCourse,UUID>{
    Page<LectureCourse> findAllByCourseNameIgnoreCase(PageRequest of,String name);
    Page<LectureCourse> findAllByCourseNameIgnoreCaseAndUserEmail(PageRequest of, String search, String name);
    Page<LectureCourse> findAllByUserEmail(PageRequest of, String name);
    long countByUserEmail(String name);
    Optional<LectureCourse> findByUserAndCourseAndGroup(User user, Course course, String group);
    @Query("SELECT c FROM Course c JOIN LectureCourse lc ON c.id=lc.course.id WHERE lc.user.id=:userId")
    List<Course> findAllByUserId(UUID userId);
    Optional<LectureCourse> findByCourseAndGroup(Course course, String group);
    Page<LectureCourse> findAllByUserEmailAndSemesterId(PageRequest of, String name, UUID fromString);
    Page<LectureCourse> findAllByCourseNameIgnoreCaseAndUserEmailAndSemesterId(PageRequest of, String search,
            String name, UUID fromString);

}
