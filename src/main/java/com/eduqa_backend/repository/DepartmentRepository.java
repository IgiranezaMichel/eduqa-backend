package com.eduqa_backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.eduqa_backend.enums.Role;
import com.eduqa_backend.modal.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
@Query("""
    SELECT 
        d.name AS departmentName,
        COALESCE(COUNT(s.id), 0) AS numberOfStudents
    FROM 
        Department d
    LEFT JOIN 
        User s
    ON 
        d.id = s.department.id
        AND s.role = :role
    GROUP BY 
        d.id, d.name
""")
public List<Object[]> findAllUserWithInDepartment(@Param("role") Role role);
}

