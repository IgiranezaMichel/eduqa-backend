package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.input.AttendanceInput;
import com.eduqa_backend.services.AttendanceServices;

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
public ResponseEntity<String> getAttendanceList(@RequestBody AttendanceInput attendanceInput) {    
    return attendanceServices.createAttendance(attendanceInput);
}
}
