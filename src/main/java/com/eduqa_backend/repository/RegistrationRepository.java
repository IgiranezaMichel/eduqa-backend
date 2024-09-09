package com.eduqa_backend.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eduqa_backend.modal.Registration;
@Repository
public interface RegistrationRepository extends JpaRepository<Registration,UUID> {

    Page<Registration> findAllBySemesterNameIgnoreCase(PageRequest of,String name);

}
