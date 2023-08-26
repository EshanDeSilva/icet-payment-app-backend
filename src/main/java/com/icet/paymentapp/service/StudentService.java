package com.icet.paymentapp.service;


import com.icet.paymentapp.dto.paginate.PaginatedResponseStudentDto;
import com.icet.paymentapp.dto.request.RequestStudentDto;
import com.icet.paymentapp.dto.response.ResponseStudentDto;

public interface StudentService {
    ResponseStudentDto saveStudent(RequestStudentDto dto, String id);
    ResponseStudentDto updateStudent(RequestStudentDto dto,String id);
    void deleteStudent(String studentId);
    ResponseStudentDto findStudent(String studentId);
    PaginatedResponseStudentDto findAllStudents(int page, int size);
    String getLastId(String course);
    String generateId(String course, String lastId);
    PaginatedResponseStudentDto searchByStudentId(int page, int size, String text);
    PaginatedResponseStudentDto searchByStudentName(int page, int size, String name);
    //search
}
