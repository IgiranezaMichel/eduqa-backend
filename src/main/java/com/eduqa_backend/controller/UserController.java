package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.dto.UserDTO;
import com.eduqa_backend.enums.Role;
import com.eduqa_backend.input.UserInput;
import com.eduqa_backend.services.UserServices;
import com.eduqa_backend.util.PageInput;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
@Autowired private UserServices userServices;
@PostMapping("register")
public ResponseEntity<String> createUser(@RequestBody UserInput userInput) {
    return userServices.registerUser(userInput);
}
@PostMapping("register/{courseId}")
public ResponseEntity<String> registerLecture(@RequestBody UserInput userInput,@PathVariable String courseId) {
    return userServices.registerUser(userInput);
}
@PostMapping("update")
public ResponseEntity<String> updateUserInformation(@RequestBody UserInput userInput) {
    return userServices.registerUser(userInput);
}
@PostMapping("get/all")
public Pagination<UserDTO> getAllUserPage(@RequestBody PageInput userInput,@RequestParam Role role) {
    return userServices.getAllUserPage(userInput,role);
}
@GetMapping("get/total/by-role")
public long countUserByRole(@RequestParam Role role) {
    return userServices.getTotalUserByRole(role);
}
}
