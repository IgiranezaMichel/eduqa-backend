package com.eduqa_backend.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eduqa_backend.modal.Course;

public interface CourseRepository extends JpaRepository<Course,UUID>{

    Page<Course> findAllByNameContainingIgnoreCase(PageRequest of,String name);
    @Query("SELECT c  FROM Course c JOIN LectureCourse lc ON lc.course.id = c.id AND lc.semester.id=:semesterId")
    Page<Course> findAvailableCourseWithInASemester(PageRequest of,UUID semesterId);
}
