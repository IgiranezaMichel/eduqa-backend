package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.eduqa_backend.dto.CourseDTO;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.input.LectureCourseInput;
import com.eduqa_backend.mapper.CoursesMapper;
// import com.eduqa_backend.mapper.LectureCourseMapper;
import com.eduqa_backend.modal.Course;
import com.eduqa_backend.modal.LectureCourse;
import com.eduqa_backend.modal.Semester;
import com.eduqa_backend.modal.User;
import com.eduqa_backend.repository.CourseRepository;
import com.eduqa_backend.repository.LectureCourseRepository;
import com.eduqa_backend.repository.SemesterRepository;
import com.eduqa_backend.repository.UserRepository;
import com.eduqa_backend.util.PageInput;
import java.security.*;
@Service
public class LectureCourseServices {
@Autowired private LectureCourseRepository lectureCourseRepository;
@Autowired private UserRepository userRepository;
@Autowired private CourseRepository courseRepository;
@Autowired private SemesterRepository semesterRepository;
// private LectureCourseMapper lectureCourseMapper = new LectureCourseMapper();
private CoursesMapper coursesMapper=new CoursesMapper();
public ResponseEntity<String> registerLectureCourses(LectureCourseInput data) {
   try {
    Course course = courseRepository.findById(UUID.fromString(data.getCourseId())).orElseThrow(() -> new RuntimeException("Course not found"));
    User user = userRepository.findById(UUID.fromString(data.getUserId())).orElseThrow(() -> new RuntimeException("Lecture not found"));
    Semester semester = semesterRepository.findById(UUID.fromString(data.getSemesterId())).orElseThrow(() -> new RuntimeException("Semester not found"));
    LectureCourse lectureCourse = lectureCourseRepository.findByUserAndCourseAndGroup(user,course,data.getGroup()).orElse(null);
    LectureCourse findGroup= lectureCourseRepository.findByCourseAndGroup(course,data.getGroup()).orElse(null);
    if(lectureCourse != null) {throw new RuntimeException("Lecture has course already!!");}
    if(findGroup != null) {throw new RuntimeException("course group has taken by"+findGroup.getUser().getName()+"!!");}
    lectureCourseRepository.save(new LectureCourse(data,user, course,semester));
    return new ResponseEntity<>("Lecture Course registered successfully", HttpStatus.OK);
   } catch (Exception e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
   }
   
}
public Pagination<CourseDTO> getLectureCoursesPage(PageInput input,String semesterId,Principal principal) {
    if (input.getSearch()!=null&&!input.getSearch().isEmpty()) {
        Page<LectureCourse>page = lectureCourseRepository.findAllByCourseNameIgnoreCaseAndUserEmailAndSemesterId(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())),input.getSearch(),principal.getName(),UUID.fromString(semesterId));
        return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(i->new CourseDTO(i.getCourse())).toList());
   }
   Page<LectureCourse>page = lectureCourseRepository.findAllByUserEmailAndSemesterId(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())),principal.getName(),UUID.fromString(semesterId));
   return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(i->new CourseDTO(i.getCourse())).toList());

 }
public long  getTotalLectureCourse(Principal principal) {
  return lectureCourseRepository.countByUserEmail(principal.getName());
}
public List<CourseDTO> getListOfLectureCourses(String id) {
return lectureCourseRepository.findAllByUserId(UUID.fromString(id)).stream().map(coursesMapper).toList();
}
}
