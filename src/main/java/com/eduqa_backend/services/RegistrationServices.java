package com.eduqa_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduqa_backend.repository.RegistrationRepository;

@Service
public class RegistrationServices {
@Autowired private RegistrationRepository registrationRepository;
}
