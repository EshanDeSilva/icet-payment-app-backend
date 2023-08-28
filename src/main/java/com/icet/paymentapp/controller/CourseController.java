package com.icet.paymentapp.controller;

import com.icet.paymentapp.dto.request.RequestCourseDto;
import com.icet.paymentapp.dto.paginate.PaginatedResponseCourseDto;
import com.icet.paymentapp.service.CourseService;
import com.icet.paymentapp.util.StandardResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(value = "/delete",params = "courseId")
    public ResponseEntity<StandardResponseEntity> deleteCourse(@RequestParam String courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(
                new StandardResponseEntity(202,"Course deleted",null),
                HttpStatus.ACCEPTED
        );
    }

    @PutMapping(value = "/update",params = {"courseId"})
    public ResponseEntity<StandardResponseEntity> updateCourse(@RequestParam String courseId, @RequestBody RequestCourseDto dto){
        return new ResponseEntity<>(
                new StandardResponseEntity(201,"Course updated",courseService.updateCourse(courseId,dto)),
                HttpStatus.CREATED
        );
    }

    @GetMapping(value = "/search",params = {"text","page","size"})
    public ResponseEntity<StandardResponseEntity> searchCourse(@RequestParam String text,@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseEntity(200,"searched course",courseService.searchCourse(text,page,size)),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/findAll",params = {"page","size"})
    public ResponseEntity<StandardResponseEntity> findAllCourses(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseEntity(200,"all courses",courseService.findAllCourses(page,size)),
                HttpStatus.OK
        );
    }
}
