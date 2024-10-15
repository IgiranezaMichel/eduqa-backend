package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.dto.StudentCourseListDTO;
import com.eduqa_backend.dto.StudentRegisteredCourseDTO;
import com.eduqa_backend.dto.UserDTO;
import com.eduqa_backend.input.StudentRegisterCourseInput;
import com.eduqa_backend.services.StudentRegisterCourseServices;
import com.eduqa_backend.util.PageInput;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/registered-student")
public class StudentRegisterCoursesController {
    @Autowired
    private StudentRegisterCourseServices services;

    @GetMapping("register/{semesterId}")
    public ResponseEntity<String> registerStudentInSemestter(@RequestParam String lectureCourseId,@PathVariable String semesterId, Principal principal) {
        return services.createCourseRegistration(lectureCourseId,semesterId,principal);
    }

    @PostMapping("get/student/{semesterId}")
    public Pagination<UserDTO> getStudentJoiningLecturePrincipalCourse(@RequestBody PageInput input,
            @PathVariable String semesterId,
            Principal principal) {
        return services.getStudentJoiningLecturePrincipalCourse(input, semesterId, principal);
    }

    @GetMapping("get/student/courses")
    List<StudentCourseListDTO> getStudentPrincipalCourseHistory(Principal principal) {
        return services.getStudentPrincipalCourseHistory(principal);
    }

    @GetMapping("getall/student/courses")
    public List<StudentCourseListDTO> getStudentPrincipalCompletedCourseHistory(Principal principal) {
        return services.getStudentPrincipalCompletedCourseHistory(principal);
    }

    @GetMapping("getall/student/registered/courses/{semesterId}")
    public List<StudentRegisteredCourseDTO> getAllStudentRegisteredCourseWithInAsemester(Principal principal,
           @PathVariable String semesterId) {
        return services.getAllStudentRegisteredCourseWithInAsemester(principal, semesterId);
    }
}
