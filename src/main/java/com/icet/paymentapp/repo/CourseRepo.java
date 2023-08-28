package com.icet.paymentapp.repo;

import com.icet.paymentapp.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CourseRepo extends JpaRepository<Course,String> {

    @Query(value = "SELECT * FROM course WHERE course_id LIKE %?1%",nativeQuery = true)
    Page<Course> searchCourse(String text,PageRequest pageRequest);

    @Query(value = "SELECT COUNT(course_id) FROM course WHERE course_id LIKE %?1%",nativeQuery = true)
    long searchCourseCount(String text);
}
