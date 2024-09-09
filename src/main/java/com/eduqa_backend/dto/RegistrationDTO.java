package com.eduqa_backend.dto;

import com.eduqa_backend.modal.Semester;
import com.eduqa_backend.modal.User;

import lombok.Getter;
import lombok.Setter;

public class RegistrationDTO extends UserDTO{
    @Getter @Setter
private SemesterDTO semester;

public RegistrationDTO(User user, Semester semester) {
    super(user);
    this.semester = new SemesterDTO(semester);
}
}
