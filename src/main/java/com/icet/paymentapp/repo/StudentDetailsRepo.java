package com.icet.paymentapp.repo;

import com.icet.paymentapp.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface StudentDetailsRepo extends JpaRepository<StudentDetails,String> {

}