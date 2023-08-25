package com.icet.paymentapp.controller;

import com.icet.paymentapp.dto.paginate.PaginatedResponseStudentDto;
import com.icet.paymentapp.dto.request.RequestStudentDto;
import com.icet.paymentapp.dto.response.ResponseStudentDto;
import com.icet.paymentapp.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseStudentDto saveStudent(@RequestBody RequestStudentDto dto){
        return studentService.saveStudent(dto, generatedId(dto.getRegisterCourse(), dto.getRegisterBatch()));
    }

    @GetMapping(params = "id") //api/v1/students/ICM1060001
    public ResponseStudentDto findStudent(@PathVariable String id){
        return studentService.findStudent(id);
    }

    @DeleteMapping(params = "id") //api/v1/students/delete?id=ICM1060001
    public String deleteStudent(@RequestParam String id){
        studentService.deleteStudent(id);
        return "delete-student";
    }

    @PutMapping(params = "id")
    public ResponseStudentDto updateStudent(@RequestBody RequestStudentDto dto,String id){
        return studentService.updateStudent(dto,id);
    }

    @GetMapping(value = "/list",params = {"page","size"})
    public PaginatedResponseStudentDto findAllStudent(@RequestParam int page, @RequestParam int size){
        return studentService.findAllStudents(page,size);
    }

    @GetMapping(params = {"course","batch"})
    public String generatedId(String course,String batch){
        return studentService.generateId(course+batch,studentService.getLastId(course+batch));
    }

}
