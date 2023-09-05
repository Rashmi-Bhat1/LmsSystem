package com.learning.system.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Length(min = 5, message = "Course name length should be min 5")
    private String courseName;
    private int courseDuration;
    @Length(min = 10, message = "Description length should be min 10s")
    private String description;
    private String technology;
    private String url;

}
