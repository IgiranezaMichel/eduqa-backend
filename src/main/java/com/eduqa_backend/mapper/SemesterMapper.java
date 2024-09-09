package com.eduqa_backend.mapper;

import java.util.function.Function;

import com.eduqa_backend.dto.SemesterDTO;
import com.eduqa_backend.modal.Semester;

public class SemesterMapper implements Function<Semester, SemesterDTO> {
    @Override
    public SemesterDTO apply(Semester semester) {
        return new SemesterDTO(semester);
    }

}
