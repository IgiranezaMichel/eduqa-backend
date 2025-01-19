package com.eduqa_backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.dto.AttendanceDTO;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.input.AttendanceInput;
import com.eduqa_backend.services.AttendanceServices;
import com.eduqa_backend.util.PageInput;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/attendance")
public class AttendanceController {
@Autowired private AttendanceServices attendanceServices;
@PostMapping("create")
public ResponseEntity<String> createAttendance(@RequestBody AttendanceInput attendanceInput) {    
    return attendanceServices.createAttendance(attendanceInput);
}
@PostMapping("list")
public Pagination<AttendanceDTO> getAttendanceList(@RequestBody PageInput page,Principal principal) {    
    return attendanceServices.getAttendanceList(page,principal);
}
}
