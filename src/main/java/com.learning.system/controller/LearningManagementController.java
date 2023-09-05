package com.learning.system.controller;

import com.learning.system.dao.AdminRepository;
import com.learning.system.dto.ManagementEvent;
import com.learning.system.entity.UserEntity;
import com.learning.system.entity.CourseEntity;
import com.learning.system.exception.UserNotFoundException;
import com.learning.system.service.LearningManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("/api/v1.0/lms")
@CrossOrigin("http://localhost:3000")
public class LearningManagementController {

    @Autowired
    private LearningManagmentService learningManagmentService;
    @Autowired
    AdminRepository repo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/company/add")
    public ResponseEntity<UserEntity> add(@Valid @RequestBody UserEntity userEntity){
        if(userEntity.getRole().isEmpty()) {
            userEntity.setRole("USER"); //default role
        }
        String encryptedPwd = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encryptedPwd);
        repo.save(userEntity);
        return new ResponseEntity<>(learningManagmentService.saveAdminDetails(userEntity), HttpStatus.CREATED);
    }

    @GetMapping("/company/get")
    public ResponseEntity<List<UserEntity>> getAll(){
        return ResponseEntity.ok( learningManagmentService.getAllDet());
    }


    @PostMapping("/course/addCourse")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CourseEntity> addUser(@Valid @RequestBody ManagementEvent managementEvent){
        return new ResponseEntity<>(learningManagmentService.saveCourseDeatils(managementEvent), HttpStatus.CREATED);
    }
    @GetMapping("/course/getDetails/byId/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public  ResponseEntity<Optional<CourseEntity>> getAllDetails(@PathVariable("id") long id)  throws UserNotFoundException {
        return ResponseEntity.ok(learningManagmentService.getCourseById(id));
    }
    @GetMapping("/course/getDetails")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<CourseEntity>> getAllDetails(){
        return ResponseEntity.ok(learningManagmentService.getAllCourse());
    }
    @DeleteMapping("/course/deleteDetails/{courseName}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteCourse(@PathVariable String courseName) throws UserNotFoundException{
         learningManagmentService.deleteCourse(courseName);
        return "deleted successfully";
    }
    @GetMapping("/course/getDetails/{tech}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public  ResponseEntity<List<CourseEntity>> getAllDetailsByTech(@PathVariable("tech") String technology) throws UserNotFoundException{
        return ResponseEntity.ok(learningManagmentService.getCourseDetails(technology));
    }

    @GetMapping("/course/getDetails/{fromTime}/{toTime}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<CourseEntity>> getAllDetailsByCourseDuration(@PathVariable("fromTime") int fromTime,
                                                                           @PathVariable("toTime") int toTime){
        return ResponseEntity.ok(learningManagmentService.getCourseDetailByDuraion(fromTime,toTime));
    }

}
