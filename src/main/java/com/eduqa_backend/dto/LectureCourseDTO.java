package com.eduqa_backend.dto;
import com.eduqa_backend.modal.LectureCourse;
import com.eduqa_backend.util.DateConverter;
import lombok.Getter;
import lombok.Setter;
public class LectureCourseDTO extends UserDTO{
@Getter @Setter
private CourseDTO course;
@Getter @Setter
private String semesterName;
@Getter @Setter
private String semesterStartingDate;
@Getter @Setter
private String semesterEndingDate;
@Getter @Setter
private String group;
public LectureCourseDTO(LectureCourse lc) {
    super(lc.getUser());
    this.course = new CourseDTO(lc.getCourse());
    this.semesterName = lc.getSemester().getName();
    this.semesterEndingDate =DateConverter.LocalDateConverter(lc.getSemester().getEndDate(), "yyy,MMM,dd");
    this.semesterStartingDate =DateConverter.LocalDateConverter(lc.getSemester().getStartingDate(), "yyy,MMM,dd");
    this.group=lc.getGroup();
}

}
