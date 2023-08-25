package com.icet.paymentapp.controller;

import com.icet.paymentapp.dto.request.RequestStudentDto;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    @PostMapping
    public String saveStudent(@RequestBody RequestStudentDto dto){

        return dto.toString();
    }
    @GetMapping("/{id}") //api/v1/students/ICM1060001
    public String findStudent(@PathVariable String id){
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

}
