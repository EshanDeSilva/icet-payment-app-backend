package com.icet.paymentapp.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDetailsKey implements Serializable {
    private String student_id;
    private String course_id;

}
