package com.eduqa_backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.eduqa_backend.enums.Role;
import com.eduqa_backend.enums.UserStatus;
import com.eduqa_backend.modal.User;
@Repository
public interface UserRepository extends JpaRepository<User,UUID>{
    Page<User> findAllByNameContainingIgnoreCase(PageRequest of,String name);
    long countByRole(Role role);
    Page<User> findAllByRoleAndNameContainingIgnoreCaseAndStatus(PageRequest of, Role role, String search,UserStatus status);
    Page<User> findAllByRoleAndStatus(PageRequest of, Role role,UserStatus status);
    List<User> findAllByRoleAndStatus(Role role,UserStatus status);
    @Query("SELECT u from User u where u.email=:email")
    Optional<User> findByEmail(String email);
    long countByRoleAndStatus(Role role, UserStatus status);
    Optional<User> findFirstByRoleOrderByTimeStampDesc(Role role);
}
