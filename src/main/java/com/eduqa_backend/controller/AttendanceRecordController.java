package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.input.AttendanceRecordInput;
import com.eduqa_backend.services.AttendanceRecordServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/attendance-record")
public class AttendanceRecordController {
@Autowired AttendanceRecordServices attendanceRecordService;
@PostMapping("create")
public ResponseEntity<String> createAttendanceRecord(@RequestBody AttendanceRecordInput attendanceRecordInput) {
   return attendanceRecordService.createAttendanceRecord(attendanceRecordInput);
}

}
