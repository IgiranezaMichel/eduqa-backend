package com.eduqa_backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import com.eduqa_backend.dto.LectureCourseOverviewDTO;
import com.eduqa_backend.modal.Course;
import com.eduqa_backend.modal.LectureCourse;
import com.eduqa_backend.modal.User;

@Repository
public interface LectureCourseRepository extends JpaRepository<LectureCourse, UUID> {
    Page<LectureCourse> findAllByCourseNameIgnoreCase(PageRequest of, String name);

    Page<LectureCourse> findAllByCourseNameIgnoreCaseAndUserEmail(PageRequest of, String search, String name);

    Page<LectureCourse> findAllByUserEmail(PageRequest of, String name);

    long countByUserEmail(String name);

    Optional<LectureCourse> findByUserAndCourseAndGroup(User user, Course course, String group);

    @Query("SELECT c FROM Course c JOIN LectureCourse lc ON c.id=lc.course.id WHERE lc.user.id=:userId")
    List<Course> findAllByUserId(UUID userId);

    Optional<LectureCourse> findByCourseAndGroup(Course course, String group);

    Page<LectureCourse> findAllByUserEmailAndSemesterId(PageRequest of, String name, UUID fromString);

    Page<LectureCourse> findAllByCourseNameIgnoreCaseAndUserEmailAndSemesterId(PageRequest of, String search,
            String name, UUID semesterId);

    @Query("""
            SELECT
                new com.eduqa_backend.dto.LectureCourseOverviewDTO(
                    lc.id,
                    lc.course.code,
                    lc.course.name,
                    lc.group,
                    lc.course.credit,
                    lc.course.duration,
                    (
                        SELECT lp.currentChapter
                        FROM LectureCourseProgressReport lp
                        WHERE lp.lectureCourseContent.lectureCourse.id = lc.id
                        ORDER BY lp.timeStamp DESC
                        LIMIT 1
                    ) AS latestChapter,
                    COUNT(src.id) AS studentCount,
                    lc.lectureCourseContent.totalChapter
                )
            FROM
                LectureCourse lc
            LEFT JOIN
                LectureCourseProgressReport lcpr ON lc.id = lcpr.lectureCourseContent.lectureCourse.id
            LEFT JOIN
                StudentRegisterCourses src ON src.lectureCourse = lc
            WHERE
                lc.semester.id = :semesterId
                AND lc.user.email = :lectureEmail
            GROUP BY
                lc.course.code,
                lc.course.name,
                lc.id,
                lc.group,
                lc.course.credit,
                lc.course.duration,
                lc.lectureCourseContent.totalChapter


                                                        """)
    Page<LectureCourseOverviewDTO> getLectureCourseDetails(PageRequest pageRequest, UUID semesterId,
            String lectureEmail);

    @Query("""
            SELECT lc FROM LectureCourse lc WHERE  lc.semester.id=:semesterId GROUP BY lc
             """)
    List<LectureCourse> findAllLectureCoursesAvailableInASemester(UUID semesterId);

    @Query("""
            SELECT lc FROM LectureCourse lc WHERE  lc.semester.id=:semesterId and lc.course.id=:courseId GROUP BY lc.group,lc
             """)
    List<LectureCourse> findAllLectureGroupCoursesAvailableInASemester(UUID semesterId, UUID courseId);

    @Query(value = """
            SELECT DISTINCT ON (lc.course_id) *
            FROM lecture_course lc
            WHERE lc.semester_id = :semesterId
            ORDER BY lc.course_id, lc.id
            """, nativeQuery = true)
    List<LectureCourse> findAllBySemesterId(@Param("semesterId") UUID semesterId);

    @Query(value = """
            SELECT DISTINCT ON (lc.user_id) *
            FROM lecture_course lc
            WHERE lc.semester_id = :semesterId
            ORDER BY lc.user_id, lc.id
            """, nativeQuery = true)
    List<LectureCourse> findAllLectureBySemesterId(@Param("semesterId") UUID semesterId);
    List<LectureCourse> findAllByUserEmail(String name);

}
