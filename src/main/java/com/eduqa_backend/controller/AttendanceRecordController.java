package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.dto.AttendanceRecordDTO;
import com.eduqa_backend.dto.AttendanceRecordHistoryDTO;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.input.AttendanceRecordInput;
import com.eduqa_backend.services.AttendanceRecordServices;
import com.eduqa_backend.util.PageInput;

import org.springframework.web.bind.annotation.PathVariable;
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
@PostMapping("list/{attendanceId}/{lectureCourseId}")
public Pagination<AttendanceRecordDTO> getAttendanceRecordList(@RequestBody PageInput pageInput,@PathVariable String attendanceId,@PathVariable String lectureCourseId) {
   return attendanceRecordService.getAttendanceRecordList(pageInput,attendanceId,lectureCourseId);
}
@PostMapping("list/history/{attendanceId}/{lectureCourseId}")
public Pagination<AttendanceRecordHistoryDTO> getAttendanceRecordHistoryList(@RequestBody PageInput pageInput,@PathVariable String attendanceId,@PathVariable String lectureCourseId) {
   return attendanceRecordService.getAttendanceRecordHistoryList(pageInput,attendanceId,lectureCourseId);
}
}
