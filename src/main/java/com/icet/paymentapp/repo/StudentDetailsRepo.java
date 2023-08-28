package com.icet.paymentapp.repo;

import com.icet.paymentapp.entity.StudentDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface StudentDetailsRepo extends JpaRepository<StudentDetails,String> {

    @Query(value = "SELECT * FROM student_details WHERE student_id LIKE %?1% OR course_id LIKE %?1%",nativeQuery = true)
    Page<StudentDetails> searchDetails(String text, PageRequest pageRequest);

    @Query(value = "SELECT COUNT(student_id) FROM student_details WHERE student_id LIKE %?1% OR course_id LIKE %?1%",nativeQuery = true)
    long searchDetailsCount(String text);
}
