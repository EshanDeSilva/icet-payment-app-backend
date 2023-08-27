package com.icet.paymentapp.service.impl;

import com.icet.paymentapp.dto.paginate.PaginatedResponsePaymentDto;
import com.icet.paymentapp.dto.request.RequestPaymentDto;
import com.icet.paymentapp.dto.response.ResponsePaymentDto;
import com.icet.paymentapp.dto.response.ResponseStudentDto;
import com.icet.paymentapp.entity.Payment;
import com.icet.paymentapp.entity.Student;
import com.icet.paymentapp.repo.PaymentRepo;
import com.icet.paymentapp.service.PaymentService;
import com.icet.paymentapp.service.StudentService;
import com.icet.paymentapp.util.IdManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo paymentRepo;
    private final StudentService studentService;
    private final IdManager idManager;

    public PaymentServiceImpl(PaymentRepo paymentRepo, StudentService studentService, IdManager idManager){
        this.paymentRepo = paymentRepo;
        this.studentService = studentService;
        this.idManager = idManager;
    }

    @Override
    public ResponsePaymentDto savePayment(RequestPaymentDto dto, String id) {
        ResponseStudentDto responseStDto = studentService.findStudent(dto.getStudentId());
        Payment payment = paymentRepo.save(
                new Payment(
                        //new PaymentKey(id),
                        id,
                        dto.getDate(),
                        dto.getAmount(),
                        dto.getPaymentType(),
                        new Student(
                                responseStDto.getStudentId(),
                                responseStDto.getNameWithInitials(),
                                responseStDto.getFullName(),
                                responseStDto.getDob(),
                                responseStDto.getNic(),
                                responseStDto.getEmail(),
                                responseStDto.getAddress(),
                                responseStDto.getWhatsAppNumber(),
                                responseStDto.getRegisteredDate(),
                                responseStDto.getParentName(),
                                responseStDto.getParentNumber()
                        )
                )
        );

        return new ResponsePaymentDto(
                payment.getPaymentId(),
                payment.getDate(),
                payment.getAmount(),
                payment.getPaymentType(),
                payment.getStudent().getStudentId()
        );
    }

    @Override
    public String generateId(String studentId,String lastId) {
        return idManager.generatePaymentId(studentId,lastId);
    }

    @Override
    public String getLastId() {
        Payment payment = paymentRepo.findTopByPaymentKeyOrderByPaymentKeyDesc();
        return payment!=null ? payment.getPaymentId():"RCPT-00000000";
    }

    @Override
    public void deletePayment(String paymentId) {
        try {
            paymentRepo.deleteById(paymentId);
        }catch (EmptyResultDataAccessException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponsePaymentDto findPayment(String paymentId) {
        Optional<Payment> payment = paymentRepo.findById(paymentId);
        return new ResponsePaymentDto(
                payment.get().getPaymentId(),
                payment.get().getDate(),
                payment.get().getAmount(),
                payment.get().getPaymentType(),
                payment.get().getStudent().getStudentId()
        );
    }

    @Override
    public PaginatedResponsePaymentDto findAllByStudentId(int page, int size, String studentId) {
        Page<Payment> payments = paymentRepo.findPaymentsByStudentId(studentId, PageRequest.of(page,size));
        long count = paymentRepo.findCountOfPaymentsByStudentId(studentId);
        List<ResponsePaymentDto> list = new ArrayList<>();
        for (Payment payment:payments) {
            list.add(new ResponsePaymentDto(
                    payment.getPaymentId(),
                    payment.getDate(),
                    payment.getAmount(),
                    payment.getPaymentType(),
                    payment.getStudent().getStudentId()
            ));
        }
        return new PaginatedResponsePaymentDto(count,list);
    }
}
