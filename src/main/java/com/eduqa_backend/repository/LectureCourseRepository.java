package com.eduqa_backend.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eduqa_backend.modal.LectureCourse;
@Repository
public interface LectureCourseRepository extends JpaRepository<LectureCourse,UUID>{

    Page<LectureCourse> findAllByCourseName(PageRequest of,String name);

}
