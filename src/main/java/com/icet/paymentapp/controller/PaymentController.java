package com.icet.paymentapp.controller;

import com.icet.paymentapp.dto.request.RequestPaymentDto;
import com.icet.paymentapp.dto.response.ResponsePaymentDto;
import com.icet.paymentapp.entity.Report;
import com.icet.paymentapp.service.PaymentService;
import com.icet.paymentapp.service.ReportService;
import com.icet.paymentapp.service.StudentService;
import com.icet.paymentapp.util.StandardResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final ReportService reportService;
    private final StudentService studentService;

    public PaymentController(PaymentService paymentService, ReportService reportService, StudentService studentService){
        this.paymentService = paymentService;
        this.reportService = reportService;
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public ResponseEntity<StandardResponseEntity> savePayment(@RequestBody RequestPaymentDto dto){
        ResponsePaymentDto responsePaymentDto = paymentService.savePayment(dto, generatedId(dto.getStudentId()));
        return new ResponseEntity<>(
                new StandardResponseEntity(201,"Payment saved", responsePaymentDto),
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
    public ResponseEntity<StandardResponseEntity> findPayment(@RequestParam String id){
        return new ResponseEntity<>(
                new StandardResponseEntity(200,"Payment Data",paymentService.findPayment(id)),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/findStudent",params = {"id","page","size"})
    public ResponseEntity<StandardResponseEntity> findAllByStudentId(@RequestParam String id,@RequestParam int page,@RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseEntity(200,"Student's Payments",paymentService.findAllByStudentId(page,size,id)),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/findAll",params = {"page","size"})
    public ResponseEntity<StandardResponseEntity> findAllPayments(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseEntity(200,"All Payments",paymentService.findAllPayments(page,size)),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/search",params = {"text","page","size"})
    public ResponseEntity<StandardResponseEntity> searchPayment(@RequestParam String text, @RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseEntity(200,"Searched Payment",paymentService.searchPayment(page,size,text)),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/report",params = "payment_id")
    public ResponseEntity<byte[]> generateReceipt(@RequestParam String payment_id){
        ResponsePaymentDto responsePaymentDto = paymentService.findPayment(payment_id);

        return reportService.generateReport(new Report(
                responsePaymentDto.getPaymentId(),
                studentService.findStudent(responsePaymentDto.getStudentId()).getFullName(),
                studentService.findStudent(responsePaymentDto.getStudentId()).getNic(),
                responsePaymentDto.getDate(),
                responsePaymentDto.getCourseAndBatch(),
                responsePaymentDto.getPaymentType(),
                responsePaymentDto.getAmount()
        ));
    }

    public String generatedId(String studentId){
        return paymentService.generateId(studentId,paymentService.getLastId());
    }
}
