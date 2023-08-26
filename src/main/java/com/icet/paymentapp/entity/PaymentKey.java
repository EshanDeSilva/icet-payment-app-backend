package com.icet.paymentapp.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentKey implements Serializable {
    private String paymentId;
}
