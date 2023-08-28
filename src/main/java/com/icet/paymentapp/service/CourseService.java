package com.icet.paymentapp.service;

import com.icet.paymentapp.dto.request.RequestCourseDto;
import com.icet.paymentapp.dto.response.ResponseCourseDto;

public interface CourseService {
    ResponseCourseDto saveCourse(RequestCourseDto dto);
    ResponseCourseDto findCourse(String courseId);
}
