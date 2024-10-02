package com.eduqa_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.dto.StudentReviewDTO;
import com.eduqa_backend.input.CourseReviewInput;
import com.eduqa_backend.services.CourseReviewServices;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/course-review")
public class CourseReviewController {
    @Autowired
    private CourseReviewServices courseReviewServices;

    @PostMapping("register")
    @Secured("ROLE_STUDENT")
    public ResponseEntity<String> createCourseReview(@RequestBody() CourseReviewInput input, Principal principal) {
        return courseReviewServices.create(input, principal);
    }

    @GetMapping("student/review")
    @Secured("ROLE_STUDENT")
    public List<StudentReviewDTO> getStudentCourseReview(Principal principal) {
        return courseReviewServices.getStudentCourseReview(principal);
    }

}
