package com.learning.system.service;

import com.learning.system.dto.ManagementEvent;
import com.learning.system.entity.UserEntity;
import com.learning.system.entity.CourseEntity;
import com.learning.system.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface LmsService {
    public CourseEntity saveCourseDeatils(ManagementEvent managementEvent);
    public String deleteCourse(String courseName) throws UserNotFoundException;
    public UserEntity saveAdminDetails(UserEntity userEntity);
    public List<UserEntity> getAllDet();
    public CourseEntity saveCourseDeatils(CourseEntity courseEntity);
    public List<CourseEntity> getCourseDetails(String technology) throws UserNotFoundException;
    public List<CourseEntity> getAllCourse();
    public List<CourseEntity> getCourseDetailByDuraion(int fromTime, int toTime);
    public Optional<CourseEntity> getCourseById(long id)  throws UserNotFoundException;
}
