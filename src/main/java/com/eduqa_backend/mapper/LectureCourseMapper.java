package com.eduqa_backend.mapper;
import java.util.function.Function;

import com.eduqa_backend.dto.LectureCourseDTO;
import com.eduqa_backend.modal.LectureCourse;
public class LectureCourseMapper implements Function<LectureCourse,LectureCourseDTO>{
    @Override
    public LectureCourseDTO apply(LectureCourse t) {
        return new LectureCourseDTO(t.getUser(), t.getCourse(),t.getSemester());
    }

}
