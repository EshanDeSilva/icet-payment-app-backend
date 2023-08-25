package com.icet.paymentapp.entity;

import lombok.*;

import javax.persistence.Column;
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

    @Column(nullable = false, length = 150)
    private String nameWithInitials;

    @Column(nullable = false, length = 200)
    private String fullName;

    @Column(nullable = false)
    private Date dob;

    @Column(length = 100)
    private String nic;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 250)
    private String address;

    @Column(nullable = false, length = 100)
    private String whatsAppNumber;

    @Column(nullable = false)
    private Date RegisteredDate;

    @Column(nullable = false, length = 150)
    private String parentName;

    @Column(nullable = false, length = 100)
    private String parentNumber;

}
