package com.eduqa_backend.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eduqa_backend.modal.User;
import com.eduqa_backend.repository.UserRepository;

@Service
public class UserDetailServices implements UserDetailsService {
    @Autowired private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User accountHolder = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Unimplemented method  loadUserByUsername"));
        return new UserDetailPrinciple(accountHolder);
    }
}