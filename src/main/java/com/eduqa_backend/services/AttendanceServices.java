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
import com.eduqa_backend.modal.LectureCourse;
import com.eduqa_backend.repository.AttendanceRepository;
import com.eduqa_backend.repository.LectureCourseRepository;
import com.eduqa_backend.util.PageInput;

@Service
public class AttendanceServices {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private LectureCourseRepository lectureCourseRepository;

    public ResponseEntity<String> createAttendance(AttendanceInput attendanceInput) {
        try {
            LectureCourse lc = lectureCourseRepository
                    .findById(UUID.fromString(attendanceInput.getLectureCourseId()))
                    .orElseThrow(() -> new RuntimeException("Lecture not found"));
            attendanceRepository.save(new Attendance(attendanceInput, lc));
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
