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
import com.eduqa_backend.dto.LectureCourseDTO;
import com.eduqa_backend.dto.LectureCourseOverviewDTO;
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
  @Autowired
  private LectureCourseRepository lectureCourseRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private SemesterRepository semesterRepository;
  // private LectureCourseMapper lectureCourseMapper = new LectureCourseMapper();
  private CoursesMapper coursesMapper = new CoursesMapper();

  public ResponseEntity<String> registerLectureCourses(LectureCourseInput data) {
    try {
      if (data.getCourseId().isEmpty())
        throw new RuntimeException("Course not found");
      Course course = courseRepository.findById(UUID.fromString(data.getCourseId()))
          .orElseThrow(() -> new RuntimeException("Course not found"));
      User user = userRepository.findById(UUID.fromString(data.getUserId()))
          .orElseThrow(() -> new RuntimeException("Lecture not found"));
      if (data.getSemesterId().isEmpty())
        throw new RuntimeException("Semester not found");
      Semester semester = semesterRepository.findById(UUID.fromString(data.getSemesterId()))
          .orElseThrow(() -> new RuntimeException("Semester not found"));
      LectureCourse lectureCourse = lectureCourseRepository.findByUserAndCourseAndGroup(user, course, data.getGroup())
          .orElse(null);
      LectureCourse findGroup = lectureCourseRepository.findByCourseAndGroup(course, data.getGroup()).orElse(null);
      if (lectureCourse != null) {
        throw new RuntimeException("Lecture has course already!!");
      }
      if (findGroup != null) {
        throw new RuntimeException("course group has taken by" + findGroup.getUser().getName() + "!!");
      }
      lectureCourseRepository.save(new LectureCourse(data, user, course, semester));
      return new ResponseEntity<>("Lecture Course registered successfully", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

  }

  public Pagination<CourseDTO> getLectureCoursesPage(PageInput input, String semesterId, Principal principal) {
    if (input.getSearch() != null && !input.getSearch().isEmpty()) {
      Page<LectureCourse> page = lectureCourseRepository.findAllByCourseNameIgnoreCaseAndUserEmailAndSemesterId(
          PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSortBy())), input.getSearch(),
          principal.getName(), UUID.fromString(semesterId));
      return new Pagination<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
          page.getContent().stream().map(i -> new CourseDTO(i.getCourse())).toList());
    }
    Page<LectureCourse> page = lectureCourseRepository.findAllByUserEmailAndSemesterId(
        PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSortBy())), principal.getName(),
        UUID.fromString(semesterId));
    return new Pagination<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
        page.getContent().stream().map(i -> new CourseDTO(i.getCourse())).toList());

  }

  public long getTotalLectureCourse(Principal principal) {
    return lectureCourseRepository.countByUserEmail(principal.getName());
  }

  public List<CourseDTO> getListOfLectureCourses(String id) {
    return lectureCourseRepository.findAllByUserId(UUID.fromString(id)).stream().map(coursesMapper).toList();
  }

  public Pagination<LectureCourseOverviewDTO> getLectureCourseDetails(PageInput input, String semesterId,
      Principal principal) {
    Page<LectureCourseOverviewDTO> page = lectureCourseRepository.getLectureCourseDetails(
          PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSortBy())),
        UUID.fromString(semesterId), principal.getName());
        
    return new Pagination<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(), page.getContent());

  }

  public List<LectureCourseDTO> getListOfAvailableCourseInASemester(String semesterId) {
    List<LectureCourse> list = lectureCourseRepository
        .findAllLectureCoursesAvailableInASemester(UUID.fromString(semesterId));
    return list.stream().map(LectureCourseDTO::new).toList();
  }

  public List<LectureCourseDTO> getAllActiveCourseGroups(String semesterId, String courseId) {
    List<LectureCourse> list = lectureCourseRepository
        .findAllLectureGroupCoursesAvailableInASemester(UUID.fromString(semesterId), UUID.fromString(courseId));
    return list.stream().map(LectureCourseDTO::new).toList();
  }

  public List<LectureCourseDTO> getListOfCourseAvailableWithInASemester(String semesterId) {
    List<LectureCourse> list =lectureCourseRepository.findAllBySemesterId(UUID.fromString(semesterId));
    return list.stream().map(LectureCourseDTO::new).toList();
  }
  public List<LectureCourseDTO> getListOfLectureAvailableWithInASemester(String semesterId) {
    List<LectureCourse> list =lectureCourseRepository.findAllLectureBySemesterId(UUID.fromString(semesterId));
    return list.stream().map(LectureCourseDTO::new).toList();
  }

public LectureCourseDTO findLectureCourseId(String lectureCourseId) {
  return lectureCourseRepository.findById(UUID.fromString(lectureCourseId)).stream().map(LectureCourseDTO::new).findFirst().orElse(null);
}
}
