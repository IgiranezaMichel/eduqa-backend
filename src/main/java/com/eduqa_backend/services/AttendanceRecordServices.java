package com.eduqa_backend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.input.AttendanceRecordInput;
import com.eduqa_backend.modal.AttendanceRecord;
import com.eduqa_backend.modal.StudentRegisterCourses;
import com.eduqa_backend.repository.AttendanceRecordRepository;
import com.eduqa_backend.repository.StudentRegisterCoursesRepository;

@Service
public class AttendanceRecordServices {
@Autowired private AttendanceRecordRepository attendanceRecordRepository;
@Autowired private StudentRegisterCoursesRepository studentRegisterCoursesRepository;

public ResponseEntity<String> createAttendanceRecord(AttendanceRecordInput attendanceRecordInput) {
   try {
    StudentRegisterCourses studentRegisterCourses = studentRegisterCoursesRepository.findById(UUID.fromString(attendanceRecordInput.getStudentCourseId())).orElseThrow(()->new Exception("Student Not Found"));
    attendanceRecordRepository.save(new AttendanceRecord(attendanceRecordInput, studentRegisterCourses));
    return ResponseEntity.ok("Attendance Record Created Successfully");
   } catch (RuntimeException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
   }
   catch(Exception e){
    e.printStackTrace();
    return ResponseEntity.badRequest().body("Something went wrong");
   }
}
}
