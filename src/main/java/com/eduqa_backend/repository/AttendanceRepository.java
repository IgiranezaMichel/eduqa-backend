package com.eduqa_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduqa_backend.modal.Attendance;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID>{

}
