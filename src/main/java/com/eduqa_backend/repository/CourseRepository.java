package com.eduqa_backend.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eduqa_backend.modal.Course;

public interface CourseRepository extends JpaRepository<Course,UUID>{

    Page<Course> findAllByName(PageRequest of,String name);

}
