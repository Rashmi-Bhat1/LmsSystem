package com.learning.system.dto;

import com.learning.system.entity.CourseEntity;
import lombok.*;

import javax.validation.Valid;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ManagementEvent {
    private String type;
    @Valid
    private CourseEntity courseEntity;

}
