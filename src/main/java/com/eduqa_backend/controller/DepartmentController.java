package com.eduqa_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.dto.DepartmentDTO;
import com.eduqa_backend.enums.Role;
import com.eduqa_backend.services.DepartmentServices;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired private DepartmentServices departmentService;
    @PostMapping("register")
    public ResponseEntity<String> registerDepartment(@RequestBody DepartmentDTO entity) {        
        return departmentService.createDepartment(entity);
    }
    @GetMapping("get/all")
    public List<DepartmentDTO> getAllDepartment() {
        return departmentService.getAllDepartment();
    }
    @GetMapping("get/all/user")
    public List<Object[]> findAllUsertWithInDepartment(@RequestParam Role role) {
        return departmentService.findAllUserWithInDepartment(role);
    }
}
