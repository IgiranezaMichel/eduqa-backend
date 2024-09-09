package com.eduqa_backend.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eduqa_backend.modal.Semester;
@Repository
public interface SemesterRepository extends JpaRepository<Semester,UUID>{

}
