package com.icet.paymentapp.controller;

import com.icet.paymentapp.dto.request.RequestStudentDto;
import com.icet.paymentapp.service.StudentService;
import com.icet.paymentapp.util.StandardResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<StandardResponseEntity> saveStudent(@RequestBody RequestStudentDto dto){
        return new ResponseEntity<>(
                new StandardResponseEntity(201, "Student Saved", studentService.saveStudent(dto, generatedId(dto.getRegisterCourse(), dto.getRegisterBatch()).getBody().getData().toString())),
                HttpStatus.CREATED
        );
    }

    @GetMapping(value = "find",params = "id") //api/v1/students/ICM1060001
    public ResponseEntity<StandardResponseEntity> findStudent(@PathVariable String id){
        return new ResponseEntity<>(
                new StandardResponseEntity(200, "Student Data", studentService.findStudent(id)),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete",params = "id") //api/v1/students/delete?id=ICM1060001
    public ResponseEntity<StandardResponseEntity> deleteStudent(@RequestParam String id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>(
                new StandardResponseEntity(202, "Student Deleted", null),
                HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/update",params = "id")
    public ResponseEntity<StandardResponseEntity> updateStudent(@RequestBody RequestStudentDto dto,String id){
        return new ResponseEntity<>(
                new StandardResponseEntity(201, "Student Updated", studentService.updateStudent(dto, id)),
                HttpStatus.CREATED);
    }

    @GetMapping(value = "/list",params = {"page","size"})
    public ResponseEntity<StandardResponseEntity> findAllStudent(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseEntity(200, "Students List", studentService.findAllStudents(page, size)),
                HttpStatus.OK);
    }

    @GetMapping(value = "/search",params = {"page","size","text"})
    public ResponseEntity<StandardResponseEntity> searchStudent(@RequestParam int page, @RequestParam int size,@RequestParam String text){
        return new ResponseEntity<>(
                new StandardResponseEntity(200, "Search Students", studentService.searchStudent(page, size, text)),
                HttpStatus.OK);
    }

    @GetMapping(value = "/getId",params = {"course","batch"})
    public ResponseEntity<StandardResponseEntity> generatedId(String course,String batch){
        return new ResponseEntity<>(
                new StandardResponseEntity(200, "Student ID", studentService.generateId(course+batch,studentService.getLastId(course+batch))),
                HttpStatus.OK);
    }

}
