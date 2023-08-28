package com.icet.paymentapp.dto.request;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestCourseDto {
    private String course;
    private String batch;
    private Date startDate;
    private double courseFee;
}
