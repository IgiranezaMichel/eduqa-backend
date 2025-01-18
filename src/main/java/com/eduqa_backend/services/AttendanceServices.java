package com.eduqa_backend.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.dto.AttendanceDTO;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.input.AttendanceInput;
import com.eduqa_backend.modal.Attendance;
import com.eduqa_backend.modal.Course;
import com.eduqa_backend.modal.StudentRegisterCourses;
import com.eduqa_backend.repository.AttendanceRepository;
import com.eduqa_backend.repository.StudentRegisterCoursesRepository;
import com.eduqa_backend.util.PageInput;

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

    public Pagination<AttendanceDTO> getAllAttendance(PageInput input) {
        LocalDateTime date = LocalDateTime.now();
        Attendance attendance = attendanceRepository.findFirstByOrderByDateDesc().orElse(null);
        if (attendance != null)
            date = attendance.getDate();
        Page<Attendance> page = attendanceRepository.findListOfAttendedStudent(
                PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSortBy())),
                date);
        return new Pagination<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
                page.getContent().parallelStream().map(
                        AttendanceDTO::new).toList());

    }
}
