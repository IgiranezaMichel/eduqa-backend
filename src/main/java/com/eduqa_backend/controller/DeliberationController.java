package com.eduqa_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduqa_backend.input.DeliberationInput;
import com.eduqa_backend.services.DeliberationServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/deliberation")
public class DeliberationController {
    @Autowired private DeliberationServices deliberationServices;
@PostMapping("create")
public ResponseEntity<String> createDeliberation(@RequestBody DeliberationInput deliberationInput) {
    return deliberationServices.createDeliberation(deliberationInput);
}

}
