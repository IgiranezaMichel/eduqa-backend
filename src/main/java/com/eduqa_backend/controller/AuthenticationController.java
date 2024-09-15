package com.eduqa_backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.dto.LoginInput;
import com.eduqa_backend.modal.User;
import com.eduqa_backend.services.UserServices;
@RestController("/api/auth")
public class AuthenticationController {
@Autowired private UserServices userServices;
    public User authenticate(LoginInput input) {
        return userServices.login(input);
    }
}
