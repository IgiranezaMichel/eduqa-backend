package com.eduqa_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.dto.CourseDTO;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.input.LectureCourseInput;
import com.eduqa_backend.services.LectureCourseServices;
import com.eduqa_backend.util.PageInput;
import java.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/lecture-course")
public class LectureCourseController {
@Autowired private LectureCourseServices lectureCourseService;
@PostMapping("register")
@PreAuthorize("hasRole('ROLE_ROLE_HOD') or hasRole('ROLE_ADMIN')")
public ResponseEntity<String> registerLectureCourses(@RequestBody LectureCourseInput data) {    
    return lectureCourseService.registerLectureCourses(data);
}
@PostMapping("all/courses")
@Secured("ROLE_INSTRUCTOR")
public Pagination<CourseDTO> getLectureCourses(Principal principal,@RequestBody PageInput input) {
    return lectureCourseService.getLectureCoursesPage(input,principal);
}
@GetMapping("total/courses")
@Secured("ROLE_INSTRUCTOR")
public long getTotalLectureCourse(Principal principal) {
    return lectureCourseService.getTotalLectureCourse(principal);
}
}
