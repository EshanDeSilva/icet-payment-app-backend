package com.icet.paymentapp.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class RequestStudentDto {
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
