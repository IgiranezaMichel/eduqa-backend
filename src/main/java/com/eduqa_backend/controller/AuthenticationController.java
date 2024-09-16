package com.eduqa_backend.controller;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eduqa_backend.dto.UserDTO;
import com.eduqa_backend.enums.Role;
import com.eduqa_backend.enums.UserStatus;
import com.eduqa_backend.input.UserInput;
import com.eduqa_backend.services.UserServices;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
public class AuthenticationController {
@Autowired private UserServices userServices;
   @RequestMapping(value="/login")
 public ResponseEntity<String> login()
 {
   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if(!auth.getName().equals("anonymousUser")&&!auth.getPrincipal().toString().equals("anonymousUser"))
    {
      return new ResponseEntity<>("login successful",HttpStatus.OK);
    }
   return new ResponseEntity<>("Wrong username or password",HttpStatus.BAD_REQUEST);
 }
 @RequestMapping("/success-login")
public UserDTO successLogin(Principal principal){
  
    return userServices.findByEmail(principal.getName())
    .stream().map(usr->new UserDTO(usr)).findFirst().orElseThrow(()->new RuntimeException("User not found"));
}
@RequestMapping("/fail-login")
public ResponseEntity<String> failLogin(){
return new ResponseEntity<>("Wrong username or password",HttpStatus.BAD_REQUEST);
}
    @GetMapping("create")
    public ResponseEntity<String> createAdmin() {
        var user=new UserInput();
        user.setEmail("admin@gmail.com");
        user.setGender("Male");
        user.setName("admin");
        user.setPassword("admin");
        user.setPhoneNumber("0783402443");
        user.setRole(Role.ROLE_ADMIN);
        user.setStatus(UserStatus.ACTIVE);
        return userServices.registerUser(user);
    }
}
