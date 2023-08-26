package com.icet.paymentapp.entity;

import com.icet.paymentapp.entity.enums.PaymentType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "payment")
public class Payment {
    @EmbeddedId
    private PaymentKey paymentKey;
    private Date date;
    private double Amount;
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id",nullable = false)
    private Student student;
}
