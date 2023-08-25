package com.icet.paymentapp.controller;

import com.icet.paymentapp.dto.request.RequestStudentDto;
import com.icet.paymentapp.dto.response.ResponseStudentDto;
import com.icet.paymentapp.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseStudentDto saveStudent(@RequestBody RequestStudentDto dto){
        ResponseStudentDto resStudent = studentService.saveStudent(dto, generatedId(dto.getRegisterCourse(), dto.getRegisterBatch()));
        return resStudent;
    }

    @GetMapping("/{id}") //api/v1/students/ICM1060001
    public String findStudent(@PathVariable String id){
        studentService.findStudent(id);
        return "find-student";
    }
    @DeleteMapping(params = "id") //api/v1/students/delete?id=ICM1060001
    public String deleteStudent(@RequestParam String id){
        return "delete-student";
    }
    @PutMapping
    public String updateStudent(@RequestBody RequestStudentDto dto){
        return "update-student";
    }
    @GetMapping(value = "/list",params = {"page","size"})
    public String findAllStudent(@RequestParam int page,@RequestParam int size){
        return "all-students";
    }

    @GetMapping(params = {"course","batch"})
    public String generatedId(String course,String batch){
        return studentService.generateId(course+batch,studentService.getLastId(course+batch));
    }

}
