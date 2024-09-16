package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.input.StudentRegisterCourseInput;
import com.eduqa_backend.services.StudentRegisterCourseServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/registered-student")
public class StudentRegisterCoursesController {
@Autowired private StudentRegisterCourseServices services;
@PostMapping("register")
public ResponseEntity<String> registerStudentInSemestter(@RequestBody StudentRegisterCourseInput entity) {
    return services.createCourseRegistration(entity);
}

}
