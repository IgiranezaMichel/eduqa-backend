package com.eduqa_backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduqa_backend.enums.Role;
import com.eduqa_backend.modal.User;
@Repository
public interface UserRepository extends JpaRepository<User,UUID>{
    Page<User> findAllByNameContainingIgnoreCase(PageRequest of,String name);
    long countByRole(Role role);
    Page<User> findAllByRoleAndNameContainingIgnoreCase(PageRequest of, Role role, String search);
    Page<User> findAllByRole(PageRequest of, Role role);
    Optional<User> findByEmail(String username);
}
