package com.eduqa_backend.dto;
import com.eduqa_backend.modal.LectureCourseProgressReport;
import com.eduqa_backend.util.DateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureCourseProgressReportDTO {
    private String id;
    private double currentChapter;
    private String title;
    private String courseName;
    private String timeStamp;
    private int duration;
    public LectureCourseProgressReportDTO(LectureCourseProgressReport in) {
        this.id = in.getId().toString();
        this.currentChapter = in.getCurrentChapter();
        this.title = in.getTitle();
        this.courseName=in.getLectureCourseContent().getLectureCourse().getCourse().getName();
        this.duration=in.getLectureCourseContent().getLectureCourse().getCourse().getDuration();
        this.timeStamp = DateConverter.LocalDateTimeConverter(in.getTimeStamp(), "yyy ,MMM-dd a");
    }

}
