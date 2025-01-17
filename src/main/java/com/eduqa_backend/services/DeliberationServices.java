package com.eduqa_backend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.input.DeliberationInput;
import com.eduqa_backend.modal.Deliberation;
import com.eduqa_backend.modal.StudentRegisterCourses;
import com.eduqa_backend.repository.DeliberationRepository;
import com.eduqa_backend.repository.StudentRegisterCoursesRepository;

@Service
public class DeliberationServices {
@Autowired private DeliberationRepository deliberationRepository;
@Autowired  private StudentRegisterCoursesRepository studentRegisterCoursesRepository;

public ResponseEntity<String> createDeliberation(DeliberationInput deliberationInput) {
      try {
            StudentRegisterCourses src = studentRegisterCoursesRepository
                    .findById(UUID.fromString(deliberationInput.getStudentRegisteredId()))
                    .orElseThrow(() -> new RuntimeException("Student not found"));
                    deliberationRepository.save(new Deliberation(deliberationInput, src));
            return ResponseEntity.ok("Deliberation created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
}
}
