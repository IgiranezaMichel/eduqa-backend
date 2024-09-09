package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.modal.Course;
import com.eduqa_backend.services.CourseServices;
import com.eduqa_backend.util.PageInput;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/course")
public class CourseController {
@Autowired private CourseServices courseServices;
public @PostMapping("register")
public ResponseEntity<String> registerCourse(@RequestBody Course entity) {    
    return courseServices.courseRegistreation(entity);
}
public @GetMapping("get/all")
public ResponseEntity<String> getAllCoursespage(@RequestBody PageInput pageInput) {    
    return courseServices.getCoursePage(pageInput);
}
}
