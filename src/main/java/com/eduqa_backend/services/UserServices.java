package com.eduqa_backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.eduqa_backend.dto.Pagination;
import com.eduqa_backend.dto.UserDTO;
import com.eduqa_backend.enums.Role;
import com.eduqa_backend.enums.UserStatus;
import com.eduqa_backend.input.UserInput;
import com.eduqa_backend.mapper.UserMapper;
import com.eduqa_backend.modal.Department;
import com.eduqa_backend.modal.User;
import com.eduqa_backend.repository.DepartmentRepository;
import com.eduqa_backend.repository.UserRepository;
import com.eduqa_backend.util.GeneratePassword;
import com.eduqa_backend.util.IdGenerator;
import com.eduqa_backend.util.PageInput;

@Service
public class UserServices {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private DepartmentRepository departmentRepository;
  @Autowired
  private EmailServices emailServices;
  // @Autowired private AuthenticationManager authenticationManager;

  private UserMapper userMapper = new UserMapper();
  private Page<User> all;

  public Page<User> getAll() {
    return all;
  }

  public void setAll(Page<User> all) {
    this.all = all;
  }

  public ResponseEntity<String> registerUser(UserInput userInput) {
    Department department = new Department();
    boolean userHasPassword = true;
    String generatedPassword = GeneratePassword.generatePassword();
    try {
      if ((userInput.getRole() != Role.ROLE_ADMIN))
        department = departmentRepository.findById(UUID.fromString(userInput.getDepartmentId()))
            .orElseThrow(() -> new Exception("Department not found"));
      User user2 = userRepository.findByEmail(userInput.getEmail()).orElse(null);
      if (user2 != null) {
        return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
      } else {
        if (userInput.getPassword() == null || userInput.getPassword().isEmpty()) {
          userInput.setPassword(BCrypt.hashpw(generatedPassword, BCrypt.gensalt()));
          userHasPassword = false;
        } else {
          userInput.setPassword(BCrypt.hashpw(userInput.getPassword(), BCrypt.gensalt()));
        }
      }
      if (userInput.getRole() == Role.ROLE_INSTRACTOR) {
        User user = userRepository.findFirstByRoleOrderByTimeStampDesc(Role.ROLE_INSTRACTOR).orElse(null);
        if (user == null) {
          userInput.setCode("LEC-00001");
        } else {
          var code = user.getCode().split("-")[1];
          String generatedId = IdGenerator.getNextId(code);
          userInput.setCode("LEC-" + generatedId);
        }
      }
      if (userInput.getRole() == Role.ROLE_HOD) {
        User user = userRepository.findFirstByRoleOrderByTimeStampDesc(Role.ROLE_HOD).orElse(null);
        if (user == null) {
          userInput.setCode("HOD-00001");
        } else {
          var code = user.getCode().split("-")[1];
          String generatedId = IdGenerator.getNextId(code);
          userInput.setCode("HOD-" + generatedId);
        }
      }
      if (userInput.getRole() == Role.ROLE_STUDENT) {
        User user = userRepository.findFirstByRoleOrderByTimeStampDesc(Role.ROLE_STUDENT).orElse(null);
        if (user == null) {
          userInput.setCode("ST-00001");
        } else {
          var code = user.getCode().split("-")[1];
          String generatedId = IdGenerator.getNextId(code);
          userInput.setCode("ST-" + generatedId);
        }
      }
      User user = userRepository.save(new User(userInput, department));
      if (userHasPassword) {
        emailServices.sendUserHavingPasswordEmailConfirmation(user);
      } else {
        emailServices.sendUserHavingNoPasswordEmailConfirmation(user, generatedPassword);
      }
      return ResponseEntity.ok("User Registered Successfully");
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  public Pagination<UserDTO> getAllUserPage(PageInput input, Role role, UserStatus status) {
    if (input.getSearch() != null && !input.getSearch().isEmpty()) {
      all = userRepository.findAllByRoleAndNameContainingIgnoreCaseAndStatus(
          PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSortBy())), role,
          input.getSearch(), status);
    } else
      all = userRepository
          .findAllByRoleAndStatus(
              PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSortBy())), role, status);
    return new Pagination<>(all.getNumber(), all.getTotalPages(), all.getTotalElements(),
        all.getContent().stream().map(userMapper).toList());
  }

  public long getTotalUserByRole(Role role, UserStatus status) {
    return userRepository.countByRoleAndStatus(role, status);
  }

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public List<UserDTO> getAllUserByRoleAndStatus(Role role, UserStatus status) {
    return userRepository.findAllByRoleAndStatus(role, status).stream().map(userMapper).toList();
  }

  public ResponseEntity<String> changeUserStatus(String userId, UserStatus status) {
    try {
      User user = userRepository.findById(UUID.fromString(userId))
          .orElseThrow(() -> new RuntimeException("User not found"));
      user.setStatus(status);
      userRepository.save(user);
      return ResponseEntity.ok("User status changed successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error changing user status");
    }
  }
  // public User login(LoginInput input) {
  // authenticationManager.authenticate(
  // new UsernamePasswordAuthenticationToken(
  // input.getEmail(),
  // input.getPassword()));

  public ResponseEntity<String> resetUserPassword(String userId) throws Exception {
  try {
     User user=userRepository.findById(UUID.fromString(userId)).orElseThrow(()->new IllegalArgumentException("Department is required"));
    String generatedPassword=GeneratePassword.generatePassword();
    user.setPassword(BCrypt.hashpw(generatedPassword, BCrypt.gensalt()));
    emailServices.sendPasswordResettingNotification(user,generatedPassword);
    return new ResponseEntity<>("User password has resetted successful",HttpStatus.OK);
  } catch (Exception e) {
    return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
  }

}

  // return userRepository.findByEmail(input.getEmail())
  // .orElseThrow();
  // }
}
