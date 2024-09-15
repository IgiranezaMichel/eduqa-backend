package com.eduqa_backend.modal;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

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
@UuidGenerator(style = Style.AUTO)
private UUID id;
private String name;
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,mappedBy = "department",targetEntity = User.class)
public List<User>users;
public Department(DepartmentDTO entity) {
    if(entity.getId()!=null&&!entity.getId().isEmpty())
    try {
        this.id=UUID.fromString(entity.getId());
    } catch (Exception e) {
        throw new RuntimeException("Invalid id");
     }
    if(entity.getName()==null||entity.getName().isEmpty())throw new RuntimeException("Name is required");
    this.name=entity.getName();
    
    }
}
