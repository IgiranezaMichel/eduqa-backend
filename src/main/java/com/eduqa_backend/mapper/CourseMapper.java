package com.eduqa_backend.mapper;

import java.util.function.Function;
import com.eduqa_backend.dto.CourseDTO;
import com.eduqa_backend.modal.Course;
public class CourseMapper implements Function<Course,CourseDTO>{

    @Override
    public CourseDTO apply(Course t) {
        return CourseDTO(t);
    }

}
