package com.eduqa_backend.modal;

import java.util.List;
import java.util.UUID;

import com.eduqa_backend.dto.DepartmentDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Department {
@Id
private UUID id;
private String name;
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "department",targetEntity = User.class)
public List<User>users;
public Department(DepartmentDTO entity) {
    this.id=UUID.fromString(entity.getId());
    if(entity.getName()!=null)throw new RuntimeException("Name is required");
    this.name=entity.getName();
    
    }
}
