package com.icet.paymentapp.dto.request;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RequestStudentDto {
    private String nameWithInitials;
    private String fullName;
    private Date dob;
    private String nic;
    private String email;
    private String address;
    private String registerCourse;
    private String registerBatch;
    private String whatsAppNumber;
    private Date RegisteredDate;
    private String parentName;
    private String parentNumber;

}
