package com.icet.paymentapp.service;

import com.icet.paymentapp.dto.request.RequestPaymentDto;
import com.icet.paymentapp.dto.response.ResponsePaymentDto;

public interface PaymentService {
    ResponsePaymentDto savePayment(RequestPaymentDto dto, String id);
    String generateId(String studentId, String lastId);
    String getLastId();
}
