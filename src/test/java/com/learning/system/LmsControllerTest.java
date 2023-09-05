package com.learning.system;

import com.learning.system.controller.LearningManagementController;
import com.learning.system.dao.AdminRepository;
import com.learning.system.dao.UserRepo;
import com.learning.system.entity.CourseEntity;
import com.learning.system.entity.UserEntity;
import com.learning.system.exception.UserNotFoundException;
import com.learning.system.service.LearningManagmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LmsControllerTest {

    @InjectMocks
    LearningManagementController classToBeTested;
    @Mock
    LearningManagmentService service;
    @Mock
    AdminRepository adminRepository;
    @Mock
    UserRepo userRepo;

    @Test
    public void shouldTestGetAdmin(){
        List<UserEntity> userEntity = createAdminMock();
        when(service.getAllDet()).thenReturn(userEntity);
        classToBeTested.getAll();
    }

    @Test
    public void shouldTestGetCourse()  {
        List<CourseEntity> courseEntityList = creatCourseMock();
        when(service.getAllCourse()).thenReturn(courseEntityList);
        classToBeTested.getAllDetails();
        assertEquals(1, service.getAllCourse().size());


    }
    @Test
    public void shouldTestCourseById() throws UserNotFoundException {
        List<CourseEntity> courseEntityList = creatCourseMock();
        when(service.getCourseById(1)).thenReturn(Optional.of(courseEntityList.get(0)));
        classToBeTested.getAllDetails(1);
        assertEquals("ABC",service.getCourseById(1).get().getCourseName());
    }
 /*   @Test
    public void shouldTestException() throws UserNotFoundException {
        List<CourseEntity> userEntityList = creatCourseMock();
        when(service.getCourseById(2)).thenThrow(UserNotFoundException.class);
        classToBeTested.getAllDtails(2);
    }*/
    @Test
    public void shouldTestCourseByTech() throws UserNotFoundException {
        List<CourseEntity> courseEntityList = creatCourseMock();
        when(service.getCourseDetails("Java")).thenReturn(courseEntityList);
        assertEquals(1,service.getCourseDetails("Java").size());
    }
    @Test
    public void shouldTestCourseByDuration(){
        List<CourseEntity> courseEntityList = creatCourseMock();
        when(service.getCourseDetailByDuraion(2,6)).thenReturn(courseEntityList);
        assertEquals("Java",service.getCourseDetailByDuraion(2,6).get(0).getTechnology());
    }

    public List<UserEntity> createAdminMock(){
        List<UserEntity> userEntityList = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setRole("ADMIN");
        userEntity.setId(1);
        userEntity.setUserName("Rashmi");
        userEntity.setPassword("Rashmi@10");
        userEntity.setEmail("rashmi@gmail.com");
        userEntityList.add(userEntity);
        return userEntityList;
    }
    public List<CourseEntity> creatCourseMock(){
        List<CourseEntity> courseEntityArrayList = new ArrayList<>();
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseDuration(10);
        courseEntity.setCourseName("ABC");
        courseEntity.setDescription("XYZ XYZ XYZ");
        courseEntity.setTechnology("Java");
        courseEntity.setId(1);
        courseEntityArrayList.add(courseEntity);
        return courseEntityArrayList;
    }
}