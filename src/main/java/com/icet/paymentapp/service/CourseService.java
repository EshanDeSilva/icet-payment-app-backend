package com.icet.paymentapp.service;

import com.icet.paymentapp.dto.paginate.PaginatedResponseCourseDto;
import com.icet.paymentapp.dto.request.RequestCourseDto;
import com.icet.paymentapp.dto.response.ResponseCourseDto;

public interface CourseService {
    ResponseCourseDto saveCourse(RequestCourseDto dto);
    ResponseCourseDto findCourse(String courseId);
    void deleteCourse(String courseId);
    ResponseCourseDto updateCourse(String courseId, RequestCourseDto dto);
    PaginatedResponseCourseDto searchCourse(String text, int page, int size);
}
