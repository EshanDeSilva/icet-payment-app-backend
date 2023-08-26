package com.icet.paymentapp.dto.request;

import com.icet.paymentapp.entity.enums.PaymentType;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RequestPaymentDto {
    //private String paymentId;
    private Date date;
    private double Amount;
    private PaymentType paymentType;
    private String studentId;
}
