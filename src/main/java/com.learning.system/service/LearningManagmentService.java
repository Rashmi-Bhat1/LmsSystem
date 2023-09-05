package com.learning.system.service;

import com.learning.system.dao.AdminRepository;
import com.learning.system.dao.UserRepo;
import com.learning.system.dto.ManagementEvent;
import com.learning.system.entity.UserEntity;
import com.learning.system.entity.CourseEntity;
import com.learning.system.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Service
public class LearningManagmentService implements LmsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    @Override
    public CourseEntity saveCourseDeatils(CourseEntity courseEntity) {
        return userRepo.save(courseEntity);
    }

    @Override
    public Optional<CourseEntity> getCourseById(long id)  throws UserNotFoundException{
       CourseEntity courseEntity = userRepo.findById(id).get();
       if(!isEmpty(courseEntity))
        return userRepo.findById(id);
       else
           throw new UserNotFoundException("user not found with id : "+id);
    }

    @Override
    public List<CourseEntity> getCourseDetails(String technology)  throws UserNotFoundException {
        List<CourseEntity> list = userRepo.findAllByTechnology(technology);
        if(!list.isEmpty()){
            return list;
        }else
            throw new UserNotFoundException("Technology is not present" +technology);
    }

    @Override
    public List<CourseEntity> getAllCourse() {
        return userRepo.findAll();
    }

    @Override
    public List<CourseEntity> getCourseDetailByDuraion(int fromTime, int toTime) {
        return userRepo.findAllByCourseDuration(fromTime,toTime);
    }

    @Override
    public CourseEntity saveCourseDeatils(ManagementEvent managementEvent) {
        CourseEntity courseEntity = userRepo.save(managementEvent.getCourseEntity());
        ManagementEvent event = new ManagementEvent("CreateCourse", courseEntity);
        kafkaTemplate.send("coursedet", event);
        return courseEntity;
    }

    @Override
    public UserEntity saveAdminDetails(UserEntity userEntity) {
        UserEntity userEntity1 = adminRepository.save(userEntity);
        kafkaTemplate.send("adminev", userEntity);
        return userEntity1;
    }

    @Override
    public List<UserEntity> getAllDet() {
        return adminRepository.findAll();
    }


    @Override
    public String deleteCourse(String courseName) throws UserNotFoundException{
        List<CourseEntity> userEntityList = getAllCourse();
        if(userEntityList.contains(courseName)) {
            userRepo.deleteByCourseName(courseName);
            return "deleted";
        }else
            throw new UserNotFoundException("Course name is not present in the system" +courseName);

    }

}
