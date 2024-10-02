package com.eduqa_backend.services;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.dto.StudentReviewDTO;
import com.eduqa_backend.input.CourseReviewInput;
import com.eduqa_backend.modal.CourseReview;
import com.eduqa_backend.modal.LectureCourse;
import com.eduqa_backend.modal.User;
import com.eduqa_backend.repository.CourseReviewRepository;
import com.eduqa_backend.repository.LectureCourseRepository;
import com.eduqa_backend.repository.UserRepository;

@Service
public class CourseReviewServices {
@Autowired private CourseReviewRepository courseReviewRepository;
@Autowired private LectureCourseRepository lectureCourseRepository;
@Autowired private UserRepository userRepository;
public ResponseEntity<String> create(CourseReviewInput input,Principal principal) {
   try {
    LectureCourse lectureCourse=lectureCourseRepository.findById(UUID.fromString(input.getLectureCourseId())).orElseThrow(()->new RuntimeException("Course not found"));
    User user=userRepository.findByEmail(principal.getName()).orElseThrow(()->new RuntimeException("User not found"));
    courseReviewRepository.save(new CourseReview(input, lectureCourse, user));
    return new ResponseEntity<>("Course review added successful",HttpStatus.CREATED);
   } catch (Exception e) {
    return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
   }
}
public List<StudentReviewDTO> getStudentCourseReview(Principal principal) {
return courseReviewRepository.findAllStudentCourseReviews(principal.getName());     
}
}
