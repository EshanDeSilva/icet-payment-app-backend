package com.icet.paymentapp.entity;

import com.icet.paymentapp.entity.enums.PaymentType;
import lombok.*;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Report extends JasperPrint {
    private String paymentId;
    private String fullName;
    private String nic;
    private Date date;
    private String course;
    private PaymentType paymentType;
    private double amount;
}
