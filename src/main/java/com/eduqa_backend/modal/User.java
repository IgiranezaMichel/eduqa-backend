package com.eduqa_backend.modal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eduqa_backend.enums.Role;
import com.eduqa_backend.enums.UserStatus;
import com.eduqa_backend.input.UserInput;
import com.eduqa_backend.util.ImageConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
private String gender;
private String phoneNumber;
private String email;
private String password;
private byte [] picture;
private Role role;
private LocalDateTime timeStamp=LocalDateTime.now();
private UserStatus status;
@ManyToOne(cascade = CascadeType.REMOVE,targetEntity = Department.class)
private Department department;

public User(UserInput userDTO,Department department) {
    if(userDTO.getId() != null) 
    this.id = UUID.fromString(userDTO.getId());
    if(userDTO.getName() == null)throw new IllegalArgumentException("Name is required");
    this.name = userDTO.getName();
    if(userDTO.getGender() == null)throw new IllegalArgumentException("Gender is required");
    this.gender = userDTO.getGender();
    if(userDTO.getPhoneNumber() == null)throw new IllegalArgumentException("Phone number is required");
    this.phoneNumber = userDTO.getPhoneNumber();
    if(userDTO.getEmail() == null)throw new IllegalArgumentException("Email is required");
    this.email = userDTO.getEmail();
    if(userDTO.getPassword() == null)throw new IllegalArgumentException("Password is required");
    this.password = userDTO.getPassword();
    if(userDTO.getPicture() == null)throw new IllegalArgumentException("Picture is required");
    this.picture = ImageConverter.convertToByteArray(userDTO.getPicture());
    if(userDTO.getRole() == null)throw new IllegalArgumentException("Role is required");
    this.role = userDTO.getRole();
    if(department == null)throw new IllegalArgumentException("Department is required");
    this.department=department;
}
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "user",targetEntity = LectureCourse.class)
public List<LectureCourse>lectureCourses;
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "user",targetEntity = Registration.class)
public List<Registration>registrations;

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
