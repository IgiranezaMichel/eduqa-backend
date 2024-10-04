package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.dto.SemesterCourseDTO;
import com.eduqa_backend.input.SemesterCourseInput;
import com.eduqa_backend.services.SemesterCourseServices;
import com.eduqa_backend.util.PageInput;
import java.util.*;
@RestController
@RequestMapping("/api/semester-courses")
public class SemesterCourseController {
    @Autowired
    SemesterCourseServices semesterCourseService;

    @PostMapping("register")
    public ResponseEntity<String> registerSemesterCourse(@RequestBody SemesterCourseInput registrationInput) {
        return semesterCourseService.registerSemesterCourse(registrationInput);
    }
@GetMapping()
public List<SemesterCourseDTO>getAllSemesterCourse(){
    return semesterCourseService.getAllSemesterCourses();
}

    @PostMapping("get/all/registration-history")
    public Pagination<SemesterCourseDTO> getAllSemesterRegisteredPage(@RequestBody PageInput input) {
        return semesterCourseService.getAllSemesterRegisteredPage(input);
    }
}
