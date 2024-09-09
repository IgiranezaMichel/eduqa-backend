package com.eduqa_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.dto.LectureCourseDTO;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.input.LectureCourseInput;
import com.eduqa_backend.services.LectureCourseServices;
import com.eduqa_backend.util.PageInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/lectureCourse")
public class LectureCourseController {
@Autowired private LectureCourseServices lectureCourseService;
@PostMapping("register")
public ResponseEntity<String> registerLectureCourses(@RequestBody LectureCourseInput data) {    
    return lectureCourseService.registerLectureCourses(data);
}
public Pagination<LectureCourseDTO> getLectureCourses(@RequestBody PageInput input) {
    return lectureCourseService.getLectureCoursesPage(input);
}
}
