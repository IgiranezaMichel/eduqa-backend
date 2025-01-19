package com.eduqa_backend.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eduqa_backend.modal.Attendance;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID>{
    @Query("""
            SELECT a FROM Attendance a where a.date=:date
            """)
    Page<Attendance> findListOfAttendedStudent(PageRequest of, LocalDateTime date);
    Optional<Attendance> findFirstByOrderByDateDesc();
    Page<Attendance> findAllByLectureCourseUserEmailOrderByDateDesc(PageRequest of, String name);

}
