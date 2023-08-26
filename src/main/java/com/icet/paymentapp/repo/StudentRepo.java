package com.icet.paymentapp.repo;

import com.icet.paymentapp.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface StudentRepo extends JpaRepository<Student,String> {
    Student findTopByStudentIdStartsWithOrderByStudentIdDesc(String prefix);
    Page<Student> findStudentsByStudentIdStartsWith(String text,PageRequest pageRequest);
    @Query(value = "SELECT COUNT(student_id) FROM student",nativeQuery = true)
    long findCountOfStudentsByStudentIdStartsWith(String text);
    Page<Student> findStudentsByFullNameContaining(String name, PageRequest pageRequest);
    @Query(value = "SELECT COUNT(full_name) FROM student",nativeQuery = true)
    long findCountOfStudentsByFullNameContaining(String name);
    //Page<Student> findStudentsByStudentNameStartsWith(String name, PageRequest of);
}
