package com.icet.paymentapp.controller;

import com.icet.paymentapp.dto.request.RequestStudentDetailsDto;
import com.icet.paymentapp.dto.response.ResponseCourseDto;
import com.icet.paymentapp.dto.response.ResponseStudentDetailsDto;
import com.icet.paymentapp.dto.response.ResponseStudentDto;
import com.icet.paymentapp.entity.Course;
import com.icet.paymentapp.entity.Student;
import com.icet.paymentapp.entity.StudentDetailsKey;
import com.icet.paymentapp.service.CourseService;
import com.icet.paymentapp.service.StudentDetailsService;
import com.icet.paymentapp.service.StudentService;
import com.icet.paymentapp.util.StandardResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/studentDetails")
public class StudentDetailsController {

    private final StudentDetailsService studentDetailsService;
    private final StudentService studentService;
    private final CourseService courseService;

    public StudentDetailsController(StudentDetailsService studentDetailsService, StudentService studentService, CourseService courseService){
        this.studentDetailsService = studentDetailsService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<StandardResponseEntity> registerStudentToCourse(@RequestBody RequestStudentDetailsDto dto){
        StudentDetailsKey key = new StudentDetailsKey(dto.getStudentId(), dto.getCourseId());
        ResponseStudentDto student = studentService.findStudent(dto.getStudentId());
        ResponseCourseDto course = courseService.findCourse(dto.getCourseId());

        ResponseStudentDetailsDto responseStudentDetailsDto = studentDetailsService.registerStudentToCourse(
                key,
                new Student(
                        student.getStudentId(),
                        student.getNameWithInitials(),
                        student.getFullName(),
                        student.getDob(),
                        student.getNic(),
                        student.getEmail(),
                        student.getAddress(),
                        student.getWhatsAppNumber(),
                        student.getRegisteredDate(),
                        student.getParentName(),
                        student.getParentNumber()
                ),
                new Course(
                        course.getCourseId(),
                        course.getCourse(),
                        course.getBatch(),
                        course.getStartDate(),
                        course.getCourseFee()
                )
        );
        return new ResponseEntity<>(
                new StandardResponseEntity(201,"student registered to course",responseStudentDetailsDto),
                HttpStatus.CREATED
        );
    }
}
