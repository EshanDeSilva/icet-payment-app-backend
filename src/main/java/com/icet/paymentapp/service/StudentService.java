package com.icet.paymentapp.service;


import com.icet.paymentapp.dto.paginate.PaginatedResponseStudentDto;
import com.icet.paymentapp.dto.request.RequestStudentDto;
import com.icet.paymentapp.dto.response.ResponseStudentDto;

public interface StudentService {
    ResponseStudentDto saveStudent(RequestStudentDto dto, String id);
    void updateStudent(RequestStudentDto dto);
    void deleteStudent(String studentId);
    ResponseStudentDto findStudent(String studentId);
    PaginatedResponseStudentDto findAllStudents(int page, int size);
    String getLastId(String course);
    String generateId(String course, String lastId);
    //search
}
