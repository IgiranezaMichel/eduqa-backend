package com.eduqa_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduqa_backend.modal.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {

}
