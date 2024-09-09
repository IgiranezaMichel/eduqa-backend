package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.eduqa_backend.dto.CourseDTO;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.mapper.CoursesMapper;
import com.eduqa_backend.modal.Course;
import com.eduqa_backend.repository.CourseRepository;
import com.eduqa_backend.util.PageInput;

@Service
public class CourseServices {
@Autowired private CourseRepository courseRepository;
private CoursesMapper courseMapper = new CoursesMapper();
public ResponseEntity<String> courseRegistreation(Course entity) {
   try {
    courseRepository.save(entity);
    return ResponseEntity.ok("Course registered successfully");
   } catch (Exception e) {
    return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
   }
}

public Pagination<CourseDTO> getCoursePage(PageInput input) {
 if (input.getSearch()!=null&&!input.getSearch().isEmpty()) {
        Page<Course>page = courseRepository.findAllByNameIgnoreCase(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())),input.getSearch());
        return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(courseMapper).toList());
 
   }
   Page<Course>page = courseRepository.findAll(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())));
   return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(courseMapper).toList());

 }
}
