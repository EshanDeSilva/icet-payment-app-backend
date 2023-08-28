package com.icet.paymentapp.service;

import com.icet.paymentapp.dto.response.ResponseStudentDetailsDto;
import com.icet.paymentapp.entity.Course;
import com.icet.paymentapp.entity.Student;
import com.icet.paymentapp.entity.StudentDetailsKey;

public interface StudentDetailsService {
    ResponseStudentDetailsDto registerStudentToCourse(StudentDetailsKey studentDetailsKey, Student student, Course course);
}
