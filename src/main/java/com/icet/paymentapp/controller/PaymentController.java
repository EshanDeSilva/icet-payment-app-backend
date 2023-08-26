package com.icet.paymentapp.controller;

import com.icet.paymentapp.dto.request.RequestPaymentDto;
import com.icet.paymentapp.service.PaymentService;
import com.icet.paymentapp.util.StandardResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/save")
    public ResponseEntity<StandardResponseEntity> savePayment(@RequestBody RequestPaymentDto dto){
        return new ResponseEntity<>(
                new StandardResponseEntity(201,"Payment saved", paymentService.savePayment(dto, generatedId(dto.getStudentId()))),
                HttpStatus.CREATED
        );
    }

    public String generatedId(String studentId){
        return paymentService.generateId(studentId,paymentService.getLastId());
    }
}
