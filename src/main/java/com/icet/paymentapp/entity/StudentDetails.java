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

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    Course course;

}
