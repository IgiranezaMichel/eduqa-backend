package com.eduqa_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.dto.LectureCourseContentDTO;
import com.eduqa_backend.input.LectureCourseContentInput;
import com.eduqa_backend.services.LectureCourseContentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping("/api/lecture-course-content")
public class LectureCourseContentController {
    @Autowired private LectureCourseContentServices lContentServices;
@PostMapping("register")
public ResponseEntity<String> registerCourseContent(@RequestBody LectureCourseContentInput entity) {
    return lContentServices.create(entity);
}
@GetMapping("get/course-content/{lectureCourse}")
public LectureCourseContentDTO getLectureCourseContent(@PathVariable String lectureCourse){
return lContentServices.getLectureCourseContent(lectureCourse);
}
}
