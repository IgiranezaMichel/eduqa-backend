package com.eduqa_backend.services;

import java.security.Principal;
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
import com.eduqa_backend.dto.UserDTO;
import com.eduqa_backend.input.StudentRegisterCourseInput;
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
    private com.eduqa_backend.repository.StudentRegisterCoursesRepository StudentRegisterCoursesRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private LectureCourseRepository rCourseRepository;

    public ResponseEntity<String> createCourseRegistration(StudentRegisterCourseInput entity) {
        try {
            Registration registration = registrationRepository.findById(UUID.fromString(entity.getRegistrationId()))
                    .orElseThrow(() -> new RuntimeException("Invalid registration number"));
            LectureCourse course = rCourseRepository.findById(UUID.fromString(entity.getLectureCourseId()))
                    .orElseThrow(() -> new RuntimeException("Invalid Course Code"));
            StudentRegisterCoursesRepository.save(new StudentRegisterCourses(entity.getId(), registration, course));
            return new ResponseEntity<>("Course saved successfull", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    public Pagination<UserDTO> getStudentJoiningLecturePrincipalCourse(PageInput input, String semesterId,
            Principal principal) {
        Page<User> page = StudentRegisterCoursesRepository
                .findAllStudentJoiningLecturePrincipalCourse(
                        PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSortBy())),
                        UUID.fromString(semesterId), principal.getName());
        return new Pagination<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
                page.getContent().stream().map(UserDTO::new).collect(Collectors.toList()));
    }
}
