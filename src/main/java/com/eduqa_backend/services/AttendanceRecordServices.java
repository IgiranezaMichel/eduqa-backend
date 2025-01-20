package com.eduqa_backend.services;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.dto.AttendanceRecordDTO;
import com.eduqa_backend.dto.AttendanceRecordHistoryDTO;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.input.AttendanceRecordInput;
import com.eduqa_backend.modal.Attendance;
import com.eduqa_backend.modal.AttendanceRecord;
import com.eduqa_backend.modal.StudentRegisterCourses;
import com.eduqa_backend.repository.AttendanceRecordRepository;
import com.eduqa_backend.repository.AttendanceRepository;
import com.eduqa_backend.repository.StudentRegisterCoursesRepository;
import com.eduqa_backend.util.PageInput;

@Service
public class AttendanceRecordServices {
@Autowired private AttendanceRecordRepository attendanceRecordRepository;
@Autowired private AttendanceRepository attendanceRepository;
@Autowired private StudentRegisterCoursesRepository studentRegisterCoursesRepository;

public ResponseEntity<String> createAttendanceRecord(AttendanceRecordInput attendanceRecordInput) {
   try {
    StudentRegisterCourses studentRegisterCourses = studentRegisterCoursesRepository.findById(UUID.fromString(attendanceRecordInput.getStudentCourseId())).orElseThrow(()->new Exception("Student Not Found"));
    Attendance attendance = attendanceRepository.findById(UUID.fromString(attendanceRecordInput.getAttendanceId())).orElseThrow(()->new Exception("Student Not Found"));
    attendanceRecordRepository.save(new AttendanceRecord(attendanceRecordInput, studentRegisterCourses,attendance));
    return ResponseEntity.ok("Attendance Record Created Successfully");
   } catch (RuntimeException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
   }
   catch(Exception e){
    e.printStackTrace();
    return ResponseEntity.badRequest().body("Something went wrong");
   }
}

public Pagination<AttendanceRecordDTO> getAttendanceRecordList(PageInput input,String attendanceId,String lectureCourseId) {
   try {
    Pageable pageable = PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSortBy()));
    Page<AttendanceRecordDTO> page = attendanceRecordRepository.getAttendanceList(pageable, UUID.fromString(attendanceId),UUID.fromString(lectureCourseId));
    return new Pagination<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
     page.getContent());
   } catch (RuntimeException e) {
    throw new RuntimeException(e.getMessage());
   }
}

public Pagination<AttendanceRecordHistoryDTO> getAttendanceRecordHistoryList(PageInput input, String attendanceId,
      String lectureCourseId) {
         try {
            Pageable pageable = PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSortBy()));
            Page<AttendanceRecordHistoryDTO> page = attendanceRecordRepository.getAttendanceHistory(pageable, UUID.fromString(attendanceId),UUID.fromString(lectureCourseId));
            return new Pagination<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
             page.getContent());
           } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
           }
}
}
