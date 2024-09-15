package com.eduqa_backend.dto;

import java.time.format.DateTimeFormatter;
import com.eduqa_backend.enums.Role;
import com.eduqa_backend.enums.UserStatus;
import com.eduqa_backend.modal.User;
import com.eduqa_backend.util.ImageConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
private String id;
private String name;
private String gender;
private String phoneNumber;
private String email;
private String picture;
private Role role;
private String timeStamp;
private String departmentId;
private String departmentName;
private int totalCourse;
private UserStatus status;
public UserDTO(User user) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
    this.id = user.getId().toString();
    this.name = user.getName();
    this.gender = user.getGender();
    this.phoneNumber = user.getPhoneNumber();
    this.email = user.getEmail();
     this.picture = ImageConverter.convertToBase64(user.getPicture());
    this.role = user.getRole();
    this.timeStamp = formatter.format(user.getTimeStamp());
    if(user.getDepartment() != null) 
    this.departmentName = user.getDepartment().getName();
    this.totalCourse = user.getLectureCourses().size();
    this.status = user.getStatus();
}

}
