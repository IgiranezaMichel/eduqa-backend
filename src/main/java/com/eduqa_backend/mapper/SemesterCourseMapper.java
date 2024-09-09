package com.eduqa_backend.mapper;
import java.util.function.Function;

import com.eduqa_backend.dto.SemesterCourseDTO;
import com.eduqa_backend.modal.SemesterCourse;
public class SemesterCourseMapper implements Function<SemesterCourse, SemesterCourseDTO> {
    @Override
    public SemesterCourseDTO apply(SemesterCourse semesterCourse) {
        return new SemesterCourseDTO(semesterCourse.getCourse(), semesterCourse.getSemester());
    }

}
