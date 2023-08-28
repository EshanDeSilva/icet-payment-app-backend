package com.icet.paymentapp.service;

import com.icet.paymentapp.dto.response.ResponseStudentDetailsDto;
import com.icet.paymentapp.entity.StudentDetailsKey;

public interface StudentDetailsService {
    ResponseStudentDetailsDto registerStudentToCourse(StudentDetailsKey studentDetailsKey);
    void deleteStudentDetail(String studentId, String courseId);
}
