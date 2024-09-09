package com.eduqa_backend.modal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import com.eduqa_backend.dto.SemesterDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Semester {
@Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
private String name;
private LocalDate startingDate;
private LocalDate endDate;
private LocalDateTime timeStamp=LocalDateTime.now();

public Semester(UUID id, String semesterName, LocalDate startingDate, LocalDate endDate) {
    this.id = id;
    this.name = semesterName;
    this.startingDate = startingDate;
    this.endDate = endDate;
}

public Semester(String semesterName, LocalDate startingDate, LocalDate endDate) {
    this.name = semesterName;
    this.startingDate = startingDate;
    this.endDate = endDate;
}

public Semester(SemesterDTO semesterDTO) {
    if(semesterDTO.getId() == null)
    this.id = UUID.fromString(semesterDTO.getId());
    this.name = semesterDTO.getSemesterName();
    if(semesterDTO.getStartingDate() != null)throw new RuntimeException("Starting Date is required");
    this.startingDate = LocalDate.parse(semesterDTO.getStartingDate());
    this.endDate = LocalDate.parse(semesterDTO.getEndDate());
}

@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = SemesterCourse.class, mappedBy = "semester")
private List<SemesterCourse>semesterCourses;

@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "semester",targetEntity = Registration.class)
public List<Registration>registrations;
}
