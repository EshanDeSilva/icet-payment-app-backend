package com.icet.paymentapp.repo;

import com.icet.paymentapp.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.stream.DoubleStream;

@Repository
@EnableJpaRepositories
public interface PaymentRepo extends JpaRepository<Payment,String> {
    @Query(value = "SELECT * FROM payment ORDER BY payment_id DESC LIMIT 1", nativeQuery = true)
    Payment findTopByPaymentKeyOrderByPaymentKeyDesc();

    @Query(value = "SELECT * FROM payment WHERE student_id =?1",nativeQuery = true)
    Page<Payment> findPaymentsByStudentId(String studentId, PageRequest pageRequest);

    @Query(value = "SELECT COUNT(payment_id) FROM payment WHERE student_id =?1",nativeQuery = true)
    long findCountOfPaymentsByStudentId(String studentId);

    @Query(value = "SELECT * FROM payment WHERE payment_id LIKE %?1% OR student_id LIKE %?1%",nativeQuery = true)
    Page<Payment> searchPayment(String text, PageRequest pageRequest);

    @Query(value = "SELECT COUNT(payment_id) FROM payment WHERE payment_id LIKE %?1% OR student_id LIKE %?1%",nativeQuery = true)
    long searchPaymentCount(String text);
}
