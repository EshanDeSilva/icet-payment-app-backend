package com.icet.paymentapp.service;

import com.icet.paymentapp.dto.paginate.PaginatedResponsePaymentDto;
import com.icet.paymentapp.dto.request.RequestPaymentDto;
import com.icet.paymentapp.dto.response.ResponsePaymentDto;

public interface PaymentService {
    ResponsePaymentDto savePayment(RequestPaymentDto dto, String id);
    String generateId(String studentId, String lastId);
    String getLastId();
    void deletePayment(String paymentId);
    ResponsePaymentDto findPayment(String paymentId);
    PaginatedResponsePaymentDto findAllByStudentId(int page, int size, String studentId);
    PaginatedResponsePaymentDto findAllPayments(int page, int size);
    PaginatedResponsePaymentDto searchPayment(int page, int size, String text);
}
