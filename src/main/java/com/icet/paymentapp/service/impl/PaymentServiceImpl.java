package com.icet.paymentapp.service.impl;

import com.icet.paymentapp.dto.request.RequestPaymentDto;
import com.icet.paymentapp.dto.response.ResponsePaymentDto;
import com.icet.paymentapp.dto.response.ResponseStudentDto;
import com.icet.paymentapp.entity.Payment;
import com.icet.paymentapp.entity.PaymentKey;
import com.icet.paymentapp.entity.Student;
import com.icet.paymentapp.repo.PaymentRepo;
import com.icet.paymentapp.service.PaymentService;
import com.icet.paymentapp.service.StudentService;
import com.icet.paymentapp.util.IdManager;
import org.springframework.stereotype.Service;

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
                        new PaymentKey(id),
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
                payment.getPaymentKey().getPaymentId(),
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
        return payment!=null ? payment.getPaymentKey().getPaymentId():"RCPT#00000000";
    }
}
