package com.eduqa_backend.services;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.dto.StudentCourseListDTO;
import com.eduqa_backend.dto.StudentRegisteredCourseDTO;
import com.eduqa_backend.dto.UserDTO;
import com.eduqa_backend.modal.LectureCourse;
import com.eduqa_backend.modal.Registration;
import com.eduqa_backend.modal.StudentRegisterCourses;
import com.eduqa_backend.modal.User;
import com.eduqa_backend.repository.LectureCourseRepository;
import com.eduqa_backend.repository.RegistrationRepository;
import com.eduqa_backend.util.PageInput;

@Service
public class StudentRegisterCourseServices {
    @Autowired
    private com.eduqa_backend.repository.StudentRegisterCoursesRepository studentRegisterCoursesRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private LectureCourseRepository rCourseRepository;
    public ResponseEntity<String> createCourseRegistration(String lectureCourseId,String semesterId, Principal principal) {
        try {
            Registration registration = registrationRepository.findByUserEmailAndSemesterId(principal.getName(),UUID.fromString(semesterId))
                    .orElseThrow(() -> new RuntimeException("Invalid registration number"));
            LectureCourse course = rCourseRepository.findById(UUID.fromString(lectureCourseId))
                    .orElseThrow(() -> new RuntimeException("Invalid Course Code"));
            if (studentRegisterCoursesRepository.findByRegistrationAndLectureCourse(registration, course).isPresent()) {
                return new ResponseEntity<>("Course already registered", HttpStatus.BAD_REQUEST);
            }
            studentRegisterCoursesRepository.save(new StudentRegisterCourses(registration, course));
            return new ResponseEntity<>("Course saved successfull", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    public Pagination<UserDTO> getStudentJoiningLecturePrincipalCourse(PageInput input, String semesterId,
            Principal principal) {
        Page<User> page = studentRegisterCoursesRepository
                .findAllStudentJoiningLecturePrincipalCourse(
                        PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSortBy())),
                        UUID.fromString(semesterId), principal.getName());
        return new Pagination<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
                page.getContent().stream().map(UserDTO::new).collect(Collectors.toList()));
    }

    public Pagination<StudentRegisteredCourseDTO> findAllStudentPrincipalCourse(PageInput input, Principal principal) {
        Page<StudentRegisterCourses> page = studentRegisterCoursesRepository.findAllStudentPrincipalCourse(
                PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSortBy())),
                principal.getName());
        return new Pagination<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
                page.getContent().stream().map(StudentRegisteredCourseDTO::new).collect(Collectors.toList()));
    }

    public List<StudentCourseListDTO> getStudentPrincipalCourseHistory(Principal principal) {
        return studentRegisterCoursesRepository.getStudentPrincipalCourseHistory(principal.getName());
    }
   public List<StudentCourseListDTO> getStudentPrincipalCompletedCourseHistory(Principal principal){
    return studentRegisterCoursesRepository.getStudentPrincipalCompletedCourseHistory(principal.getName());
   }
   
   public List<StudentRegisteredCourseDTO>getAllStudentRegisteredCourseWithInAsemester(Principal principal,String semesterId){
    return studentRegisterCoursesRepository.
    findAllByRegistrationUserEmailAndRegistrationSemesterId(principal.getName(),UUID.fromString(semesterId))
    .stream().map(StudentRegisteredCourseDTO::new).toList();
   }
}
