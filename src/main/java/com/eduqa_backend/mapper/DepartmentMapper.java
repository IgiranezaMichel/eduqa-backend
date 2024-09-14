package com.eduqa_backend.mapper;

import java.util.function.Function;

import com.eduqa_backend.dto.DepartmentDTO;
import com.eduqa_backend.modal.Department;

public class DepartmentMapper implements Function<Department,DepartmentDTO>{
@Override
public DepartmentDTO apply(Department department) {
	return new DepartmentDTO(department);
}
}
