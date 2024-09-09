package com.eduqa_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.dto.RegistrationDTO;
import com.eduqa_backend.input.RegistrationInput;
import com.eduqa_backend.services.RegistrationServices;
import com.eduqa_backend.util.PageInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    @Autowired private RegistrationServices registrationService;
@PostMapping("register")
public ResponseEntity<String> registerStudent(@RequestBody RegistrationInput registrationInput) {
return registrationService.registerStudent(registrationInput);
}
@PostMapping("get/all/registration-history")
public Pagination<RegistrationDTO> getLectureCourses(@RequestBody PageInput input) {
    return registrationService.getLectureCoursesPage(input);
}

}
