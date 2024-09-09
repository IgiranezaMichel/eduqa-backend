package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.dto.SemesterDTO;
import com.eduqa_backend.mapper.SemesterMapper;
import com.eduqa_backend.modal.Semester;
import com.eduqa_backend.repository.SemesterRepository;
import com.eduqa_backend.util.PageInput;

@Service
public class SemesterServices {
@Autowired private SemesterRepository semesterRepository;
private SemesterMapper semesterMapper = new SemesterMapper();
public ResponseEntity<String> registerSemester(Semester semester) {
try {
      semesterRepository.save(semester);
      return ResponseEntity.ok("Semester registered successfully");
} catch (Exception e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);    
}
}
public Pagination<SemesterDTO> getAllSemesterRegisteredPage(PageInput input) {
       if (input.getSearch()==null) {
        Page<Semester>page = semesterRepository.findAllByName(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())),input.getSearch());
        return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(semesterMapper).toList());
 
   }
   Page<Semester>page = semesterRepository.findAll(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())));
   return new Pagination<>(page.getNumber(),page.getTotalPages(),page.getTotalElements(),page.getContent().stream().map(semesterMapper).toList());
 }
}
