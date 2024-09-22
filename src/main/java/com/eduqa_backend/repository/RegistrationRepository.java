package com.eduqa_backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.eduqa_backend.dto.DualValueChartDTO;
import com.eduqa_backend.enums.Role;
import com.eduqa_backend.modal.Registration;
import com.eduqa_backend.modal.User;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
    Page<Registration> findAllBySemesterNameIgnoreCase(PageRequest of, String name);

    Optional<Registration> findByUserIdAndSemesterId(UUID id, UUID id2);

    @Query("SELECT u  FROM User u JOIN Registration r ON u.id = r.user.id AND r.semester.id=:semesterId where u.role=:role")
    Page<User> getAvailabelUserRegisterdForASemesterPage(PageRequest of, UUID semesterId, Role role);
    @Query("""
        SELECT 
        new com.eduqa_backend.dto.DualValueChartDTO( CASE 
        WHEN r.user.id IS NOT NULL THEN 'Registered' 
        ELSE 'Not Registered' 
        END AS registrationStatus, 
        COUNT(u.id) AS count)

        FROM User u
        LEFT JOIN Registration r ON u.id = r.user.id AND r.semester.id =:semesterId
        where u.role=:role
        GROUP BY registrationStatus
            """)
    List<DualValueChartDTO> getSemesterRegistrationCountStatus(Role role, UUID semesterId);
    Page<Registration> findAllByUserEmail(String name, PageRequest of);
}
