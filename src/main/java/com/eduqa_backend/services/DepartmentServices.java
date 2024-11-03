package com.eduqa_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.dto.CourseDTO;
import com.eduqa_backend.dto.DepartmentDTO;
import com.eduqa_backend.enums.Role;
import com.eduqa_backend.modal.Department;
import com.eduqa_backend.repository.DepartmentRepository;

@Service
public class DepartmentServices {
@Autowired private DepartmentRepository departmentRepository;
public ResponseEntity<String> createDepartment(DepartmentDTO entity) {
   try {
    departmentRepository.save(new Department(entity));
    return ResponseEntity.ok("Department created successfully");
   } catch (Exception e) {
    return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
   }
}

public List<DepartmentDTO> getAllDepartment() {
   return departmentRepository.findAll().stream().map(DepartmentDTO::new).toList();
}
public List<Object[]> findAllUserWithInDepartment(Role role){
   return departmentRepository.findAllUserWithInDepartment(role);
}

public List<CourseDTO> findAllCoursesAvailableForASemester(String semesterId) {
    throw new UnsupportedOperationException("Unimplemented method 'findAllCoursesAvailableForASemester'");
}
}
