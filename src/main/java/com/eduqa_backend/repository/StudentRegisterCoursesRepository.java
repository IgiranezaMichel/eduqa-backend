package com.eduqa_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.eduqa_backend.modal.StudentRegisterCourses;
import com.eduqa_backend.modal.User;
@Repository
public interface StudentRegisterCoursesRepository extends JpaRepository<StudentRegisterCourses,UUID>{
  @Query("""
            SELECT src.registration.user FROM 
            StudentRegisterCourses src JOIN LectureCourse lc ON 
            src.lectureCourse.id = lc.id WHERE lc.semester.id = :semesterId AND lc.user.email = :lectureEmail
            """)
    Page<User> findAllStudentJoiningLecturePrincipalCourse(PageRequest of, UUID semesterId,String lectureEmail);
    @Query("""
            SELECT src FROM 
            StudentRegisterCourses src  WHERE src.registration.user.email = :studentEmail
            """)
    Page<StudentRegisterCourses> findAllStudentPrincipalCourse(PageRequest of,String studentEmail);
}
