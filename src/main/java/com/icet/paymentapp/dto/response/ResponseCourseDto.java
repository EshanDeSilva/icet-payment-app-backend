package com.icet.paymentapp.dto.response;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseCourseDto {
    private String courseId;
    private String course;
    private String batch;
    private Date startDate;
    private double courseFee;
}
