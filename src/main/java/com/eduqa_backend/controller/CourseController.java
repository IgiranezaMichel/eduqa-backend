package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eduqa_backend.dto.CourseDTO;
import com.eduqa_backend.dto.Pagination;
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
@PostMapping("register")
public ResponseEntity<String> registerCourse(@RequestBody Course entity) {    
    return courseServices.courseRegistreation(entity);
}
@PostMapping("get/all")
public Pagination<CourseDTO> getAllCoursespage(@RequestBody PageInput pageInput) {    
    return courseServices.getCoursePage(pageInput);
}
@GetMapping("get/tatal")
public long getTotalCourse() {    
    return courseServices.countCourse();
}
}
