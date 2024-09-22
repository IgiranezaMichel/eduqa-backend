package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.dto.UserDTO;
import com.eduqa_backend.input.StudentRegisterCourseInput;
import com.eduqa_backend.services.StudentRegisterCourseServices;
import com.eduqa_backend.util.PageInput;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.*;

@RestController
@RequestMapping("/api/registered-student")
public class StudentRegisterCoursesController {
    @Autowired
    private StudentRegisterCourseServices services;

    @PostMapping("register")
    public ResponseEntity<String> registerStudentInSemestter(@RequestBody StudentRegisterCourseInput entity) {
        return services.createCourseRegistration(entity);
    }

    @PostMapping("get/student/{semesterId}")
    public Pagination<UserDTO> getStudentJoiningLecturePrincipalCourse(@RequestBody PageInput input,@PathVariable String semesterId,
            Principal principal) {
        return services.getStudentJoiningLecturePrincipalCourse(input, semesterId, principal);
    }
}
