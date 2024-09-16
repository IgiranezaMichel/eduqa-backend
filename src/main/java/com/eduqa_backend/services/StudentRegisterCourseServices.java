package com.eduqa_backend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.input.StudentRegisterCourseInput;
import com.eduqa_backend.modal.Course;
import com.eduqa_backend.modal.Registration;
import com.eduqa_backend.modal.StudentRegisterCourses;
import com.eduqa_backend.repository.CourseRepository;
import com.eduqa_backend.repository.RegistrationRepository;

@Service
public class StudentRegisterCourseServices {
@Autowired private com.eduqa_backend.repository.StudentRegisterCoursesRepository StudentRegisterCoursesRepository;
@Autowired private RegistrationRepository registrationRepository;
@Autowired private CourseRepository courseRepository;
public ResponseEntity<String> createCourseRegistration(StudentRegisterCourseInput entity) {
try {
    Registration registration=registrationRepository.findById(UUID.fromString(entity.getRegistrationId())).orElseThrow(()->new RuntimeException("Invalid registration number"));
    Course course=courseRepository.findById(UUID.fromString(entity.getCourseId())).orElseThrow(()->new RuntimeException("Invalid Course Code"));
    StudentRegisterCoursesRepository.save(new StudentRegisterCourses(entity.getId(),registration,course));
    return new ResponseEntity<>("Course saved successfull",HttpStatus.CREATED);
} catch (Exception e) {
   return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
}

}
}
