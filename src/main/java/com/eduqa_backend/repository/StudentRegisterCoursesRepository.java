package com.eduqa_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.eduqa_backend.dto.StudentCourseListDTO;
import com.eduqa_backend.dto.StudentRegisteredCourseDTO;
import com.eduqa_backend.modal.StudentRegisterCourses;
import com.eduqa_backend.modal.User;

@Repository
public interface StudentRegisterCoursesRepository extends JpaRepository<StudentRegisterCourses, UUID> {
        @Query("""
                        SELECT src.registration.user FROM
                        StudentRegisterCourses src JOIN LectureCourse lc ON
                        src.lectureCourse.id = lc.id WHERE lc.semester.id = :semesterId AND lc.user.email = :lectureEmail
                        """)
        Page<User> findAllStudentJoiningLecturePrincipalCourse(PageRequest of, UUID semesterId, String lectureEmail);

        @Query("""
                        SELECT src FROM
                        StudentRegisterCourses src  WHERE src.registration.user.email = :studentEmail
                        """)
        Page<StudentRegisterCourses> findAllStudentPrincipalCourse(PageRequest of, String studentEmail);

        @Query("""
                         SELECT new com.eduqa_backend.dto.StudentCourseListDTO(c.code,c.name,c.duration,c.credit,lc.user.name,lc.user.picture,lc.user.email,
                         CASE
                                WHEN src.id IS NOT NULL THEN 'Studied'
                                ELSE 'On track'
                                END)
                         FROM Course c LEFT JOIN LectureCourse lc
                        on c.id=lc.course.id LEFT JOIN StudentRegisterCourses src on src.registration.user.email=:email
                        GROUP BY c.code,c.name,c.duration,c.credit,lc.user.name,lc.user.picture,lc.user.email,src.id
                                """)
        List<StudentCourseListDTO> getStudentPrincipalCourseHistory(String email);

        @Query("""
                         SELECT new com.eduqa_backend.dto.StudentCourseListDTO(c.code,c.name,c.duration,c.credit,lc.user.name,lc.user.picture,lc.user.email,
                         CASE
                                WHEN src.id IS NOT NULL THEN 'Studied'
                                ELSE 'On track'
                                END)
                         FROM Course c LEFT JOIN LectureCourse lc
                        on c.id=lc.course.id  JOIN StudentRegisterCourses src on src.registration.user.email=:email
                        GROUP BY c.code,c.name,c.duration,c.credit,lc.user.name,lc.user.picture,lc.user.email,src.id
                                """)
        List<StudentCourseListDTO> getStudentPrincipalCompletedCourseHistory(String email);

        List<StudentRegisterCourses> findAllByRegistrationUserEmailAndRegistrationSemesterId(String name,
                        UUID fromString);
}
