package com.icet.paymentapp.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RequestStudentDetailsDto {
    private String studentId;
    private String courseId;
}
