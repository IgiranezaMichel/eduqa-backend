package com.eduqa_backend.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eduqa_backend.modal.User;
@Repository
public interface UserRepository extends JpaRepository<User,UUID>{

    Page<User> findAllByName(PageRequest of);

}
