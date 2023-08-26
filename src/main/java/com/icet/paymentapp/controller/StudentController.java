package com.icet.paymentapp.controller;

import com.icet.paymentapp.dto.paginate.PaginatedResponseStudentDto;
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

    @PostMapping(value = "/save")
    public ResponseStudentDto saveStudent(@RequestBody RequestStudentDto dto){
        return studentService.saveStudent(dto, generatedId(dto.getRegisterCourse(), dto.getRegisterBatch()));
    }

    @GetMapping(value = "find",params = "id") //api/v1/students/ICM1060001
    public ResponseStudentDto findStudent(@PathVariable String id){
        return studentService.findStudent(id);
    }

    @DeleteMapping(value = "/delete",params = "id") //api/v1/students/delete?id=ICM1060001
    public String deleteStudent(@RequestParam String id){
        studentService.deleteStudent(id);
        return "delete-student";
    }

    @PutMapping(value = "/update",params = "id")
    public ResponseStudentDto updateStudent(@RequestBody RequestStudentDto dto,String id){
        return studentService.updateStudent(dto,id);
    }

    @GetMapping(value = "/list",params = {"page","size"})
    public PaginatedResponseStudentDto findAllStudent(@RequestParam int page, @RequestParam int size){
        return studentService.findAllStudents(page,size);
    }

    @GetMapping(value = "/searchId",params = {"page","size","id"})
    public PaginatedResponseStudentDto searchByStudentId(@RequestParam int page, @RequestParam int size,@RequestParam String id){
        return studentService.searchByStudentId(page,size,id);
    }

    @GetMapping(value = "/searchName",params = {"page","size","name"})
    public PaginatedResponseStudentDto searchByStudentName(@RequestParam int page, @RequestParam int size,@RequestParam String name){
        return studentService.searchByStudentName(page,size,name);
    }

    @GetMapping(value = "/getId",params = {"course","batch"})
    public String generatedId(String course,String batch){
        return studentService.generateId(course+batch,studentService.getLastId(course+batch));
    }

}
