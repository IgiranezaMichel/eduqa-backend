package com.eduqa_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.eduqa_backend.modal.StudentRegisterCourses;
@Repository
public interface StudentRegisterCoursesRepository extends JpaRepository<StudentRegisterCourses,UUID>{

}
