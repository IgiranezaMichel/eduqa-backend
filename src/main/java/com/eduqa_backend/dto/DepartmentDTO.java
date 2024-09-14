package com.eduqa_backend.dto;

import com.eduqa_backend.modal.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
private String id;
private String name;
public DepartmentDTO(Department department){
    this.id = department.getId().toString();
    this.name = department.getName();
}
}
