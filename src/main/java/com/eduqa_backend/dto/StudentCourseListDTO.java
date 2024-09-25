package com.eduqa_backend.dto;

import com.eduqa_backend.util.ImageConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCourseListDTO {
    private Object courseCode;
    private Object courseName;
    private Object courseDuration;
    private Object courseCredit;
    private Object lectureName;
    private Object lecturePicture;
    private Object lectureEmail;
    private Object status;
    public String getLecturePicture(){
        return ImageConverter.convertToBase64((byte [])lecturePicture);
    }
}
