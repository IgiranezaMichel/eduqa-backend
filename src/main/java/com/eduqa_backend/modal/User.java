package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eduqa_backend.enums.*;
import com.eduqa_backend.input.UserInput;
import com.eduqa_backend.util.ImageConverter;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
@Id
@UuidGenerator(style =Style.AUTO)
private UUID id;
private String name;
@Column(unique = true)
private String code;
private String gender;
private String phoneNumber;
private String email;
private String password;
private byte [] picture;
@Enumerated(value = EnumType.STRING)
private Role role;
private LocalDateTime timeStamp=LocalDateTime.now();
@Enumerated(value = EnumType.STRING)
private UserStatus status;

@ManyToOne(cascade = CascadeType.ALL,targetEntity = Department.class,optional = true,fetch = FetchType.LAZY)
private Department department;

public User(UserInput userDTO,Department department) {
    if(userDTO.getId() != null) 
    this.id = UUID.fromString(userDTO.getId());
    if(userDTO.getName() == null||userDTO.getName().isEmpty())throw new IllegalArgumentException("Name is required");
    this.name = userDTO.getName();
    if(userDTO.getGender() == null||userDTO.getGender().isEmpty())throw new IllegalArgumentException("Gender is required");
    this.gender = userDTO.getGender();
    if(userDTO.getPhoneNumber() == null||userDTO.getPhoneNumber().isEmpty())throw new IllegalArgumentException("Phone number is required");
    this.phoneNumber = userDTO.getPhoneNumber();
    if(userDTO.getEmail() == null||userDTO.getEmail().isEmpty())throw new IllegalArgumentException("Email is required");
    this.email = userDTO.getEmail();
    if(userDTO.getPassword() == null||userDTO.getPassword().isEmpty())throw new IllegalArgumentException("Password is required");
    this.password = userDTO.getPassword();
    if(userDTO.getPicture() == null||userDTO.getPicture().isEmpty())throw new IllegalArgumentException("Picture is required");
    this.picture = ImageConverter.convertToByteArray(userDTO.getPicture());
    if(userDTO.getRole() == null||userDTO.getRole().name().isEmpty())throw new IllegalArgumentException("Role is required");
    this.role = userDTO.getRole();
    if(userDTO.getRole()==Role.ROLE_ADMIN){
        this.department=null;}
    else if(department.getId() == null){
            throw new IllegalArgumentException("Department is required");
    }
    this.department=department;
    this.status=userDTO.getStatus();
    this.code=userDTO.getCode();
}
@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user",targetEntity = LectureCourse.class)
public List<LectureCourse>lectureCourses;

@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "user",targetEntity = Registration.class)
public List<Registration>registrations;

@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "user",targetEntity = LectureCourseSuggestion.class)
public List<LectureCourseSuggestion> lectureCourseSuggestions;

@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "user",targetEntity = CourseReview.class)
private List<CourseReview>courseReviews;
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
 return Collections.singletonList(new SimpleGrantedAuthority(this.role.toString()));
}
@Override
public String getUsername() {
 return this.email;
}
@Override
public boolean isAccountNonExpired() {
  return true;
}
@Override
public boolean isAccountNonLocked() {
    return true;

}
@Override
public boolean isCredentialsNonExpired() {
    return true;

}
@Override
public boolean isEnabled() {
    return true;

}
}
