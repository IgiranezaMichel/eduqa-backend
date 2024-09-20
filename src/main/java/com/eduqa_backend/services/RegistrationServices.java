package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.eduqa_backend.dto.*;
import com.eduqa_backend.enums.Role;
import com.eduqa_backend.input.RegistrationInput;
import com.eduqa_backend.mapper.RegistrationMapper;
import com.eduqa_backend.mapper.UserMapper;
import com.eduqa_backend.modal.*;
import com.eduqa_backend.repository.*;
import com.eduqa_backend.util.PageInput;
import java.util.UUID;
@Service
public class RegistrationServices {
@Autowired private RegistrationRepository registrationRepository;
@Autowired private UserRepository userRepository;
@Autowired private SemesterRepository semesterRepository;
private RegistrationMapper registrationMapper = new RegistrationMapper();
private UserMapper userMapper=new UserMapper();
public ResponseEntity<String> registerStudent(RegistrationInput registrationInput) {
   try {
    User user = userRepository.findById(UUID.fromString(registrationInput.getUserId())).orElseThrow(() -> new RuntimeException("Student not found"));
    Semester semester = semesterRepository.findById(UUID.fromString(registrationInput.getSemesterId())).orElseThrow(() -> new RuntimeException("Semester not found"));
    Registration existingRegistration = registrationRepository.findByUserIdAndSemesterId(user.getId(), semester.getId()).orElse(null);
    if (existingRegistration != null) {
       return new ResponseEntity<>("Student is already registered for this semester", HttpStatus.BAD_REQUEST);
    }
    registrationRepository.save(new Registration(user, semester));
    return ResponseEntity.ok("Registration successful");
} catch (Exception e) {
   return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
   }
   
}

public Pagination<RegistrationDTO> getLectureCoursesPage(PageInput input) {
      if (input.getSearch()==null) {
        Page<Registration>page = registrationRepository.findAllBySemesterNameIgnoreCase(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())), input.getSearch());
        return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(registrationMapper).toList());
 
   }
   Page<Registration>page = registrationRepository.findAll(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())));
   return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(registrationMapper).toList());
 }

public Pagination<UserDTO> getAvailabelUserRegisteredForASemesterPage(PageInput input, String semesterId,
        Role role) {
         Page<User>page = registrationRepository.getAvailabelUserRegisterdForASemesterPage(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())),UUID.fromString(semesterId),role);
         return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(userMapper).toList()); 
}
}

