package com.icet.paymentapp.controller;

import com.icet.paymentapp.dto.request.RequestPaymentDto;
import com.icet.paymentapp.service.PaymentService;
import com.icet.paymentapp.util.StandardResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(value = "/delete",params = "id")
    public ResponseEntity<StandardResponseEntity> deletePayment(@RequestParam String id){
        paymentService.deletePayment(id);
        return new ResponseEntity<>(
                new StandardResponseEntity(202,"Payment Deleted",null),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(value = "/find",params = "id")
    public ResponseEntity<StandardResponseEntity> findPayment(String id){
        return new ResponseEntity<>(
                new StandardResponseEntity(200,"Payment Data",paymentService.findPayment(id)),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/findStudent",params = {"id","page","size"})
    public ResponseEntity<StandardResponseEntity> findAllByStudentId(String id,int page, int size){
        return new ResponseEntity<>(
                new StandardResponseEntity(200,"Student's Payments",paymentService.findAllByStudentId(page,size,id)),
                HttpStatus.OK
        );
    }

    public String generatedId(String studentId){
        return paymentService.generateId(studentId,paymentService.getLastId());
    }
}
