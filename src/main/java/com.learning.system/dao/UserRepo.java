package com.learning.system.dao;

import com.learning.system.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<CourseEntity,Long> {
    List<CourseEntity> findAllByTechnology(String technology);

    void deleteByCourseName(String courseName);

    @Query("SELECT t FROM CourseEntity t WHERE t.courseDuration BETWEEN :minDuration AND :maxDuration")
    List<CourseEntity> findAllByCourseDuration(@Param("minDuration") int minDuration, @Param("maxDuration") int maxDuration);
}
