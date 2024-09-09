package com.eduqa_backend.dto;

import com.eduqa_backend.modal.Semester;
import com.eduqa_backend.util.DateConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDTO {
private String id;
private String semesterName;
private String startingDate;
private String endDate;
private String timeStamp;
public SemesterDTO(Semester semester) {
    this.id = semester.getId().toString();
    this.semesterName = semester.getSemesterName();
    this.startingDate = DateConverter.LocalDateConverter(semester.getStartingDate(), "dd, MMMM yyyy");
    this.endDate =DateConverter.LocalDateConverter(semester.getEndDate(), "dd, MMMM yyyy");
    this.timeStamp = DateConverter.LocalDateTimeConverter(semester.getTimeStamp(), "dd, MMMM yyyy MM:ss a");;
}

}
