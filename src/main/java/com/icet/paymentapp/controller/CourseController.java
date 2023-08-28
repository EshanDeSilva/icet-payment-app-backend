package com.icet.paymentapp.controller;

import com.icet.paymentapp.dto.request.RequestCourseDto;
import com.icet.paymentapp.service.CourseService;
import com.icet.paymentapp.util.StandardResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping("/save")
    public ResponseEntity<StandardResponseEntity> saveCourse(@RequestBody RequestCourseDto dto){
        return new ResponseEntity<>(
                new StandardResponseEntity(201,"Course Saved",courseService.saveCourse(dto)),
                HttpStatus.CREATED
        );
    }
}
