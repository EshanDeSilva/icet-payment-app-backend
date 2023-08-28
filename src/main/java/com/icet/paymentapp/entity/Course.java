package com.icet.paymentapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Course {
    @Id
    private String courseId;

    @Column(length = 200,nullable = false)
    private String course;

    @Column(length = 200,nullable = false)
    private String batch;

    @Column(nullable = false)
    private Date startDate;

    @Column(length = 200)
    private double courseFee;


    @OneToMany(mappedBy = "course")
    List<StudentDetails> students;

    public Course(String courseId, String course, String batch, Date startDate, double courseFee) {
        this.courseId = courseId;
        this.course = course;
        this.batch = batch;
        this.startDate = startDate;
        this.courseFee = courseFee;
    }

}
