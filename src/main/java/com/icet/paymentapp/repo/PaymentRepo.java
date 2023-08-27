package com.icet.paymentapp.repo;

import com.icet.paymentapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PaymentRepo extends JpaRepository<Payment,String> {
    @Query(value = "SELECT * FROM payment ORDER BY payment_id DESC LIMIT 1", nativeQuery = true)
    Payment findTopByPaymentKeyOrderByPaymentKeyDesc();
}
