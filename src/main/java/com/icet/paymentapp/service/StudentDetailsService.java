package com.icet.paymentapp.service;

import com.icet.paymentapp.dto.paginate.PaginatedResponseStudentDetailsDto;
import com.icet.paymentapp.dto.response.ResponseStudentDetailsDto;
import com.icet.paymentapp.entity.StudentDetailsKey;

public interface StudentDetailsService {
    ResponseStudentDetailsDto registerStudentToCourse(StudentDetailsKey studentDetailsKey);
    void deleteStudentDetail(String studentId, String courseId);
    PaginatedResponseStudentDetailsDto findAllDetails(int page, int size);
    PaginatedResponseStudentDetailsDto searchDetails(String text, int page, int size);
}
