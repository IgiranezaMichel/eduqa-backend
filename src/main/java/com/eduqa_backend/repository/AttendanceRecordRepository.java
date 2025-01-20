package com.eduqa_backend.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eduqa_backend.dto.AttendanceRecordDTO;
import com.eduqa_backend.dto.AttendanceRecordHistoryDTO;
import com.eduqa_backend.modal.AttendanceRecord;
@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, UUID> {
@Query("""
    SELECT new com.eduqa_backend.dto.AttendanceRecordDTO(
            attr.id, 
            attr.isPresent, 
            attr.timeStamp,
            src.id,
            src.registration.user
        ) FROM StudentRegisterCourses src  left join AttendanceRecord attr ON src.id=
    attr.studentCourse.id AND attr.attendance.id=:attendanceId WHERE src.lectureCourse.id=:lectureCourseId
        """)
Page<AttendanceRecordDTO> getAttendanceList(Pageable pageable,UUID attendanceId,UUID lectureCourseId);

@Query("""
    SELECT new com.eduqa_backend.dto.AttendanceRecordHistoryDTO(
            attr.id, 
            attr.isPresent, 
            attr.timeStamp,
            src.id,
            src.registration.user,
            attr.attendance.date
        ) FROM StudentRegisterCourses src  right join AttendanceRecord attr ON src.id=
    attr.studentCourse.id AND attr.attendance.id=:attendanceId WHERE src.lectureCourse.id=:lectureCourseId
        """)
Page<AttendanceRecordHistoryDTO> getAttendanceHistory(Pageable pageable,UUID attendanceId,UUID lectureCourseId);   
}
