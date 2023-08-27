package com.icet.paymentapp.service.impl;

import com.icet.paymentapp.dto.paginate.PaginatedResponseStudentDto;
import com.icet.paymentapp.dto.request.RequestStudentDto;
import com.icet.paymentapp.dto.response.ResponseStudentDto;
import com.icet.paymentapp.entity.Student;
import com.icet.paymentapp.repo.StudentRepo;
import com.icet.paymentapp.service.StudentService;
import com.icet.paymentapp.util.IdManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        try{
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
        }catch (DataIntegrityViolationException ex){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseStudentDto updateStudent(RequestStudentDto dto,String id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        if (optionalStudent.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Student student = optionalStudent.get();
        student.setNameWithInitials(dto.getNameWithInitials());
        student.setFullName(dto.getFullName());
        student.setDob(dto.getDob());
        student.setNic(dto.getNic());
        student.setEmail(dto.getEmail());
        student.setAddress(dto.getAddress());
        student.setWhatsAppNumber(dto.getWhatsAppNumber());
        student.setRegisteredDate(dto.getRegisteredDate());
        student.setParentName(dto.getParentName());
        student.setParentNumber(dto.getParentNumber());

        student = studentRepo.save(student);

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
    public void deleteStudent(String studentId) {
        try {
            studentRepo.deleteById(studentId);
        }catch (EmptyResultDataAccessException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseStudentDto findStudent(String studentId) {
        Optional<Student> student = studentRepo.findById(studentId);
        if (student.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseStudentDto(
                student.get().getStudentId(),
                student.get().getNameWithInitials(),
                student.get().getFullName(),
                student.get().getDob(),
                student.get().getNic(),
                student.get().getEmail(),
                student.get().getAddress(),
                student.get().getWhatsAppNumber(),
                student.get().getRegisteredDate(),
                student.get().getParentName(),
                student.get().getParentNumber()
        );
    }

    @Override
    public PaginatedResponseStudentDto findAllStudents(int page, int size) {
        long count = studentRepo.count();
        Page<Student> all = studentRepo.findAll(PageRequest.of(page, size));
        List<ResponseStudentDto> list = new ArrayList<>();
        for (Student student:all) {
            list.add(new ResponseStudentDto(
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
            ));
        }
        return new PaginatedResponseStudentDto(count,list);
    }

    @Override
    public String getLastId(String courseAndBatch) {
        return studentRepo.findTopByStudentIdStartsWithOrderByStudentIdDesc(courseAndBatch)!=null ? studentRepo.findTopByStudentIdStartsWithOrderByStudentIdDesc(courseAndBatch).getStudentId():"null-000000";
    }

    @Override
    public String generateId(String courseAndBatch, String lastId) {
        return idManager.generateStudentId(courseAndBatch,lastId);
    }

    @Override
    public PaginatedResponseStudentDto searchStudent(int page, int size, String text) {
        Page<Student> all = studentRepo.searchStudentsByText(text, PageRequest.of(page, size));
        long count = studentRepo.searchCountOfStudentsByText(text);
        List<ResponseStudentDto> list = new ArrayList<>();

        for (Student student:all) {
            list.add(new ResponseStudentDto(
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
            ));
        }
        return new PaginatedResponseStudentDto(count,list);
    }
}
