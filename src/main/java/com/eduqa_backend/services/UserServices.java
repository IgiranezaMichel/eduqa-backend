package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.dto.UserDTO;
import com.eduqa_backend.input.UserInput;
import com.eduqa_backend.mapper.UserMapper;
import com.eduqa_backend.modal.User;
import com.eduqa_backend.repository.UserRepository;
import com.eduqa_backend.util.PageInput;

@Service
public class UserServices {
@Autowired private UserRepository userRepository;
private UserMapper userMapper=new UserMapper();
private Page<User> all;

public Page<User> getAll() {
    return all;
}

public void setAll(Page<User> all) {
    this.all = all;
}

public ResponseEntity<String> registerUser(UserInput userInput) {
  try {
    userRepository.save(new User(userInput));
    return ResponseEntity.ok("User Registered Successfully");
  } catch (Exception e) {
    return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
  }
}

public Pagination<UserDTO> getAllUserPage(PageInput input) {
    if(input.getSearch()!=null){
        all = userRepository.findAllByName(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())),input.getSearch());
    }
   all = userRepository.findAll(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSortBy())));
return new Pagination<>(all.getNumber(),all.getTotalPages(),all.getTotalElements(),all.getContent().stream().map(userMapper).toList());
}
}
