package com.eduqa_backend.modal;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import com.eduqa_backend.input.DeliberationInput;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Deliberation {
@Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = StudentRegisterCourses.class)
private StudentRegisterCourses studentCourse;
private boolean hasPassed; 
public Deliberation(DeliberationInput deliberationInput,StudentRegisterCourses studentCourse) {
    if(!deliberationInput.getId().equals(""))this.id=UUID.fromString(deliberationInput.getId());
    this.studentCourse = studentCourse;
    this.hasPassed = deliberationInput.isHasPassed();
}
}
