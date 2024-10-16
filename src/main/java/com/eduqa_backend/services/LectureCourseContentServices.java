package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.dto.LectureCourseContentDTO;
import com.eduqa_backend.input.LectureCourseContentInput;
import com.eduqa_backend.modal.LectureCourse;
import com.eduqa_backend.modal.LectureCourseContent;
import com.eduqa_backend.repository.LectureCourseContentRepository;
import com.eduqa_backend.repository.LectureCourseRepository;
import java.util.*;
@Service
public class LectureCourseContentServices {
@Autowired private LectureCourseContentRepository lectureCourseContentRepository;
@Autowired private LectureCourseRepository lRepository;

public ResponseEntity<String> create(LectureCourseContentInput entity) {
   try {
     LectureCourse lc=lRepository.findById(UUID.fromString(entity.getLectureCourseContentId())).orElseThrow(()->new RuntimeException("Course not found"));
     lectureCourseContentRepository.save(new LectureCourseContent(entity, lc));
return new ResponseEntity<>("Course content saved successful",HttpStatus.CREATED);
   } catch (Exception e) {
    return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
   }
}

public LectureCourseContentDTO getLectureCourseContent(String lectureCourse) {
   LectureCourse lc=lRepository.findById(UUID.fromString(lectureCourse)).orElse(null);
	Optional<LectureCourseContent>lecture=lectureCourseContentRepository.findByLectureCourse(lc);
   return lecture.stream().map(LectureCourseContentDTO::new).findFirst().orElse(null);
}


}
