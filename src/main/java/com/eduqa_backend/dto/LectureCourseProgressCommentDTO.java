package com.eduqa_backend.dto;

import com.eduqa_backend.modal.LectureCourseProgressComment;
import com.eduqa_backend.util.DateConverter;
import com.eduqa_backend.util.ImageConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureCourseProgressCommentDTO {
    private String id;
    private String userPhoto;
    private String userName;
    private String userEmail;
    private String comment;
    private String timeStamp;
    private String lectureCourseContentId;
    private String lectureName;
    private String courseName;
    public LectureCourseProgressCommentDTO(LectureCourseProgressComment lcpr) {
        this.id = lcpr.getId().toString();
        this.userName =lcpr.getUser().getName();
        this.userEmail=lcpr.getUser().getEmail();
        this.userPhoto =ImageConverter.convertToBase64(lcpr.getUser().getPicture());
        this.comment = lcpr.getMessage();
        this.courseName=lcpr.getLectureCourseProgressReport().getLectureCourseContent().getLectureCourse().getCourse().getName();
        this.lectureName=lcpr.getLectureCourseProgressReport().getLectureCourseContent().getLectureCourse().getUser().getName();
        this.lectureCourseContentId=lcpr.getLectureCourseProgressReport().getId().toString();
        this.timeStamp = DateConverter.LocalDateTimeConverter(lcpr.getTimeStamp(), "dd, MMMM-yyyy a");
    }
}
