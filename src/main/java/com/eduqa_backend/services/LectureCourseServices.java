package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.UUID;

import com.eduqa_backend.dto.LectureCourseDTO;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.input.LectureCourseInput;
import com.eduqa_backend.mapper.LectureCourseMapper;
import com.eduqa_backend.modal.Course;
import com.eduqa_backend.modal.LectureCourse;
import com.eduqa_backend.modal.User;
import com.eduqa_backend.repository.CourseRepository;
import com.eduqa_backend.repository.LectureCourseRepository;
import com.eduqa_backend.repository.UserRepository;
import com.eduqa_backend.util.PageInput;

@Service
public class LectureCourseServices {
@Autowired private LectureCourseRepository lectureCourseRepository;
@Autowired private UserRepository userRepository;
@Autowired private CourseRepository courseRepository;
private LectureCourseMapper lectureCourseMapper = new LectureCourseMapper();
public ResponseEntity<String> registerLectureCourses(LectureCourseInput data) {
   try {
    Course course = courseRepository.findById(UUID.fromString(data.getCourseId())).orElseThrow(() -> new RuntimeException("Course not found"));
    User user = userRepository.findById(UUID.fromString(data.getUserId())).orElseThrow(() -> new RuntimeException("Lecture not found"));
    lectureCourseRepository.save(new LectureCourse(user, course));
    return new ResponseEntity<>("Lecture Course registered successfully", HttpStatus.OK);
   } catch (Exception e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
   }
   
}
public Pagination<LectureCourseDTO> getLectureCoursesPage(PageInput input) {
    if (input.getSearch()!=null) {
        Page<LectureCourse>page = lectureCourseRepository.findAllByCourseName(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())),input.getSearch());
        return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(lectureCourseMapper).toList());
 
   }
   Page<LectureCourse>page = lectureCourseRepository.findAll(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())));
   return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(lectureCourseMapper).toList());

 }
}
