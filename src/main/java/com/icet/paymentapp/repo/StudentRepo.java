package com.icet.paymentapp.repo;

import com.icet.paymentapp.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface StudentRepo extends JpaRepository<Student,String> {
    Student findTopByStudentIdStartsWithOrderByStudentIdDesc(String prefix);

    @Query(value = "SELECT * FROM student WHERE student_id LIKE %?1% OR full_name LIKE %?1%",nativeQuery = true)
    Page<Student> searchStudentsByText(String text,PageRequest pageRequest);

    @Query(value = "SELECT COUNT(student_id) FROM student WHERE student_id LIKE %?1% OR full_name LIKE %?1%",nativeQuery = true)
    long searchCountOfStudentsByText(String text);
}
