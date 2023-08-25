package com.icet.paymentapp.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    @Id
    private String studentId;
    private String nameWithInitials;
    private String fullName;
    private Date dob;
    private String nic;
    private String email;
    private String whatsAppNumber;
    private Date RegisteredDate;
    private String parentName;
    private String parentNumber;
}
