package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Registration {
@Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = User.class)
private User user;
@ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = Semester.class)
private Semester semester;
private LocalDateTime timeStamp=LocalDateTime.now();

public Registration(User user, Semester semester) {
    this.user = user;
    this.semester = semester;
    this.timeStamp=LocalDateTime.now();
}
public Registration(String id,User user, Semester semester) {
    this.id=UUID.fromString(id);
    this.user = user;
    this.semester = semester;
    this.timeStamp=LocalDateTime.now();
}

}
