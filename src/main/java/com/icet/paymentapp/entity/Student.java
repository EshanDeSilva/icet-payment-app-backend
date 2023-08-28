package com.icet.paymentapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
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
    private Date registeredDate;

    @Column(nullable = false, length = 150)
    private String parentName;

    @Column(nullable = false, length = 100)
    private String parentNumber;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();


    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    List<StudentDetails> courses;

    public Student(String studentId, String nameWithInitials, String fullName, Date dob, String nic, String email, String address, String whatsAppNumber, Date registeredDate, String parentName, String parentNumber) {
        this.studentId = studentId;
        this.nameWithInitials = nameWithInitials;
        this.fullName = fullName;
        this.dob = dob;
        this.nic = nic;
        this.email = email;
        this.address = address;
        this.whatsAppNumber = whatsAppNumber;
        this.registeredDate = registeredDate;
        this.parentName = parentName;
        this.parentNumber = parentNumber;
    }
}
