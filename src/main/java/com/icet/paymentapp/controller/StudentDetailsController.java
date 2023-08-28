package com.icet.paymentapp.controller;

import com.icet.paymentapp.dto.request.RequestStudentDetailsDto;
import com.icet.paymentapp.dto.response.ResponseStudentDetailsDto;
import com.icet.paymentapp.entity.StudentDetailsKey;
import com.icet.paymentapp.service.StudentDetailsService;
import com.icet.paymentapp.util.StandardResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/studentDetails")
public class StudentDetailsController {

    private final StudentDetailsService studentDetailsService;

    public StudentDetailsController(StudentDetailsService studentDetailsService){
        this.studentDetailsService = studentDetailsService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<StandardResponseEntity> registerStudentToCourse(@RequestBody RequestStudentDetailsDto dto){
        StudentDetailsKey key = new StudentDetailsKey(dto.getStudentId(), dto.getCourseId());
        ResponseStudentDetailsDto responseStudentDetailsDto = studentDetailsService.registerStudentToCourse(key);
        return new ResponseEntity<>(
                new StandardResponseEntity(201,"student registered to course",responseStudentDetailsDto),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(value = "/delete",params = {"studentId","courseId"})
    public ResponseEntity<StandardResponseEntity> deleteStudentDetails(@RequestParam String studentId, @RequestParam String courseId){
        studentDetailsService.deleteStudentDetail(studentId,courseId);
        return new ResponseEntity<>(
                new StandardResponseEntity(202,"student's course deleted",null),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(value = "/findAll",params = {"page","size"})
    public ResponseEntity<StandardResponseEntity> findAllStudentDetails(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseEntity(200,"all student details",studentDetailsService.findAllDetails(page,size)),
                HttpStatus.OK
        );
    }

    public ResponseEntity<StandardResponseEntity> searchStudentDetails(){
        return null;
    }
}
