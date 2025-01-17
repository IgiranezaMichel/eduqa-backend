package com.eduqa_backend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.input.AttendanceInput;
import com.eduqa_backend.modal.Attendance;
import com.eduqa_backend.modal.StudentRegisterCourses;
import com.eduqa_backend.repository.AttendanceRepository;
import com.eduqa_backend.repository.StudentRegisterCoursesRepository;

@Service
public class AttendanceServices {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private StudentRegisterCoursesRepository studentRegisterCoursesRepository;

    public ResponseEntity<String> createAttendance(AttendanceInput attendanceInput) {
        try {
            StudentRegisterCourses src = studentRegisterCoursesRepository
                    .findById(UUID.fromString(attendanceInput.getStudentRegisteredId()))
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            attendanceRepository.save(new Attendance(attendanceInput, src));
            return ResponseEntity.ok("Attendance created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }

    }
}
