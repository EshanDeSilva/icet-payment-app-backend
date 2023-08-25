package com.icet.paymentapp.service.impl;

import com.icet.paymentapp.dto.paginate.PaginatedResponseStudentDto;
import com.icet.paymentapp.dto.request.RequestStudentDto;
import com.icet.paymentapp.dto.response.ResponseStudentDto;
import com.icet.paymentapp.entity.Student;
import com.icet.paymentapp.repo.StudentRepo;
import com.icet.paymentapp.service.StudentService;
import com.icet.paymentapp.util.IdManager;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final IdManager idManager;

    public StudentServiceImpl(StudentRepo studentRepo,IdManager idManager){
        this.studentRepo = studentRepo;
        this.idManager = idManager;
    }

    @Override
    public ResponseStudentDto saveStudent(RequestStudentDto dto,String id) {
        Student student = studentRepo.save(new Student(
                id,
                dto.getNameWithInitials(),
                dto.getFullName(),
                dto.getDob(),
                dto.getNic(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getWhatsAppNumber(),
                dto.getRegisteredDate(),
                dto.getParentName(),
                dto.getParentNumber()
        ));
        return new ResponseStudentDto(
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
        );
    }

    @Override
    public void updateStudent(RequestStudentDto dto) {

    }

    @Override
    public void deleteStudent(String studentId) {

    }

    @Override
    public ResponseStudentDto findStudent(String studentId) {
        return null;
    }

    @Override
    public PaginatedResponseStudentDto findAllStudents(int page, int size) {
        return null;
    }

    @Override
    public String getLastId(String courseAndBatch) {
        return studentRepo.findTopByStudentIdStartsWithOrderByStudentIdDesc(courseAndBatch)!=null ? studentRepo.findTopByStudentIdStartsWithOrderByStudentIdDesc(courseAndBatch).getStudentId():"null-000000";
    }

    @Override
    public String generateId(String courseAndBatch, String lastId) {
        return idManager.generate(courseAndBatch,lastId);
    }
}