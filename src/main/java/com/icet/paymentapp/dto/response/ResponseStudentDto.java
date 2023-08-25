package com.icet.paymentapp.dto.response;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseStudentDto {
    private String studentId;
    private String nameWithInitials;
    private String fullName;
    private Date dob;
    private String nic;
    private String email;
    private String address;
    private String whatsAppNumber;
    private Date RegisteredDate;
    private String parentName;
    private String parentNumber;
}
