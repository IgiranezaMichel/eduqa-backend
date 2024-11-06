package com.eduqa_backend.modal;

import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import com.eduqa_backend.input.CourseReviewInput;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseReview {
    @UuidGenerator(style = Style.AUTO)
    @Id
    private UUID id;
    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity =LectureCourse.class,optional = true )
    private LectureCourse lectureCourse;
    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity =User.class,optional = true )
    private User user;
    private double marks;
    public CourseReview(CourseReviewInput input,LectureCourse lectureCourse,User user){
        if(!input.getId().isEmpty())
        this.id=UUID.fromString(input.getId());
        this.user=user;
        this.lectureCourse=lectureCourse;
        if(input.getMarks()==0)throw new RuntimeException("Marks is empty");
        this.marks=input.getMarks();
    }
}
