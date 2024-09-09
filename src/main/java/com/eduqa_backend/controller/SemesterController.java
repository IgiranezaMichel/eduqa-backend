package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.dto.SemesterDTO;
import com.eduqa_backend.modal.Semester;
import com.eduqa_backend.services.SemesterServices;
import com.eduqa_backend.util.PageInput;

@RestController 
@RequestMapping("/api/semester")
public class SemesterController {
@Autowired private SemesterServices semesterService;
public ResponseEntity<String> createSemester(@RequestBody Semester semester){
    return semesterService.registerSemester(semester);
}
@PostMapping
public Pagination<SemesterDTO>getAllSemesterRegisteredPage(@RequestBody PageInput input){
    return semesterService.getAllSemesterRegisteredPage(input);
}
}
