package com.eduqa_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@CrossOrigin
@RequestMapping("/api/semester")
public class SemesterController {
@Autowired private SemesterServices semesterService;
@PostMapping("register")
public ResponseEntity<String> createSemester(@RequestBody Semester semester){
    return semesterService.registerSemester(semester);
}
@PostMapping("all/page")
public Pagination<SemesterDTO>getAllSemesterRegisteredPage(@RequestBody PageInput input){
    return semesterService.getAllSemesterRegisteredPage(input);
}
@GetMapping("all")
public List<SemesterDTO>getAllSemester(){
    return semesterService.getAllSemester();
}
@GetMapping("get/current-semester")
public  SemesterDTO lastRegisteredSemester(){
    return semesterService.lastRegisteredSemester();
}
}
