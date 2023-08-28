package com.icet.paymentapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetails {
    @EmbeddedId
    private StudentDetailsKey key;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    Course course;

}
