package com.eduqa_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eduqa_backend.dto.CourseDTO;
import com.eduqa_backend.dto.LectureCourseDTO;
import com.eduqa_backend.dto.LectureCourseOverviewDTO;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.input.LectureCourseInput;
import com.eduqa_backend.services.LectureCourseServices;
import com.eduqa_backend.util.PageInput;
import java.security.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin()
@RestController
@RequestMapping("/api/lecture-course")
public class LectureCourseController {
@Autowired private LectureCourseServices lectureCourseService;
@PostMapping("register")
@PreAuthorize("hasRole('ROLE_ROLE_HOD') or hasRole('ROLE_ADMIN')")
public ResponseEntity<String> registerLectureCourses(@RequestBody LectureCourseInput data) {    
    return lectureCourseService.registerLectureCourses(data);
}
@GetMapping("all/{id}")
@PreAuthorize("hasRole('ROLE_ROLE_HOD') or hasRole('ROLE_ADMIN')")
public List<CourseDTO> getListOfLectureCourses(@PathVariable String id) {
    return lectureCourseService.getListOfLectureCourses(id);
}
@GetMapping("all")
@PreAuthorize("hasRole('ROLE_INSTRACTOR')")
public List<LectureCourseDTO> getListOfLectureCourses(Principal principal) {
    return lectureCourseService.getListOfLecturePrincipleCourses(principal);
}
@GetMapping("lecture-course/{lectureCourseId}")
public LectureCourseDTO findById(@PathVariable String lectureCourseId) {
    return lectureCourseService.findLectureCourseId(lectureCourseId);
}

@PostMapping("all/courses/{semesterId}")
@Secured("ROLE_INSTRACTOR")
public Pagination<CourseDTO> getLectureCourses(Principal principal,@PathVariable String semesterId,@RequestBody PageInput input) {
    return lectureCourseService.getLectureCoursesPage(input,semesterId,principal);
}
@GetMapping("total/courses")
@Secured("ROLE_INSTRACTOR")
public long getTotalLectureCourse(Principal principal) {
    return lectureCourseService.getTotalLectureCourse(principal);
}
@Secured("ROLE_INSTRACTOR")
@PostMapping("detail/{semesterId}")
public Pagination<LectureCourseOverviewDTO> getLectureCourseDetails(@RequestBody PageInput page,@PathVariable String semesterId,Principal principal){
    return lectureCourseService.getLectureCourseDetails(page, semesterId, principal);
}
@GetMapping("semester/{semesterId}")
public List<LectureCourseDTO>getListOfCourseAvailableInASemester(@PathVariable String semesterId){
    return lectureCourseService.getListOfAvailableCourseInASemester(semesterId);
}
@GetMapping("semester/course/{semesterId}")
public List<LectureCourseDTO>getListOfCourseAvailableWithInASemester(@PathVariable String semesterId){
    return lectureCourseService.getListOfCourseAvailableWithInASemester(semesterId);
}
@GetMapping("semester/lecture/{semesterId}")
public List<LectureCourseDTO>getListOfLectureAvailableWithInASemester(@PathVariable String semesterId){
    return lectureCourseService.getListOfLectureAvailableWithInASemester(semesterId);
}
@GetMapping("find/groups/{courseId}")
public List<LectureCourseDTO> getAllGroups(@RequestParam String semesterId,@PathVariable String courseId) {
    return lectureCourseService.getAllActiveCourseGroups(semesterId,courseId);
}

}
