package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.dto.SemesterCourseDTO;
import com.eduqa_backend.input.SemesterCourseInput;
import com.eduqa_backend.mapper.SemesterCourseMapper;
import com.eduqa_backend.modal.Course;
import com.eduqa_backend.modal.Semester;
import com.eduqa_backend.modal.SemesterCourse;
import com.eduqa_backend.repository.CourseRepository;
import com.eduqa_backend.repository.SemesterCourseRepository;
import com.eduqa_backend.repository.SemesterRepository;
import com.eduqa_backend.util.PageInput;
import java.util.UUID;
@Service
public class SemesterCourseServices {
@Autowired private SemesterCourseRepository semesterCourseRepository;
@Autowired private SemesterRepository semesterRepository;
@Autowired private CourseRepository courseRepository;
private SemesterCourseMapper semesterCourseMapper=new SemesterCourseMapper();
public ResponseEntity<String> registerSemesterCourse(SemesterCourseInput registrationInput) {
try {
    Course course=courseRepository.findById(UUID.fromString(registrationInput.getCourseId())).orElseThrow(()->new RuntimeException("Course not found"));
    Semester semester=semesterRepository.findById(UUID.fromString(registrationInput.getSemesterId())).orElseThrow(()->new RuntimeException("Semester not found"));
    semesterCourseRepository.save(new SemesterCourse(semester, course));
    return new ResponseEntity<>(null, HttpStatus.OK);
} catch (Exception e) {
   return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
}
}

public Pagination<SemesterCourseDTO> getAllSemesterRegisteredPage(PageInput input) {
       if (input.getSearch()==null) {
        Page<SemesterCourse>page = semesterCourseRepository.findAllBySemesterName(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())));
        return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(semesterCourseMapper).toList());
 
   }
   Page<SemesterCourse>page = semesterCourseRepository.findAll(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())));
   return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(semesterCourseMapper).toList());
 }
}
